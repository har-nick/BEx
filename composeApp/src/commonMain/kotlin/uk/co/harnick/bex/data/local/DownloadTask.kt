package uk.co.harnick.bex.data.local

import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.copyTo
import io.github.vinceglb.filekit.exists
import io.github.vinceglb.filekit.utils.div
import io.github.vinceglb.filekit.utils.toFile
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.contentType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.io.Source
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import net.lingala.zip4j.ZipFile
import uk.co.harnick.bandkit.core.BandKit
import uk.co.harnick.bandkit.core.BandKit.Encoding
import uk.co.harnick.bandkit.library.fetchItemDownloadLinks
import uk.co.harnick.bex.data.remote.DownloadState
import uk.co.harnick.bex.data.remote.DownloadState.Cancelled
import uk.co.harnick.bex.data.remote.DownloadState.Failed.Error
import uk.co.harnick.bex.data.remote.DownloadState.Failed.InvalidContentType
import uk.co.harnick.bex.data.remote.DownloadState.Failed.MissingDownloadUrl
import uk.co.harnick.bex.data.remote.DownloadState.Finished
import uk.co.harnick.bex.data.remote.DownloadState.InProgress.Downloading
import uk.co.harnick.bex.data.remote.DownloadState.InProgress.Extracting
import uk.co.harnick.bex.data.remote.DownloadState.InProgress.FetchingUrl
import uk.co.harnick.bex.data.remote.DownloadState.Queued
import uk.co.harnick.bex.data.remote.isValid
import uk.co.harnick.bex.domain.model.LibraryItem
import uk.co.harnick.bex.domain.model.LibraryItem.ItemType.Track
import kotlin.coroutines.cancellation.CancellationException

class DownloadTask(
    val item: LibraryItem,
    val bandKit: BandKit,
    val params: DownloadParams
) {
    private var job: Job? = null

    val state: StateFlow<DownloadState>
        field = MutableStateFlow<DownloadState>(Queued())

    // Bandcamp's library data returns a gateway URL to downloads, which needs to be scraped.
    private suspend fun resolveDownloadUrl(libraryItem: LibraryItem): Pair<Encoding, String>? {
        val allLinks = bandKit.fetchItemDownloadLinks(libraryItem.gatewayUrl)
        val firstMatch = params.encodingPriority.firstNotNullOfOrNull { encoding ->
            allLinks[encoding]?.let { encoding to it }
        }

        return firstMatch
    }

    private suspend fun downloadItem(
        url: String,
        cacheTarget: Path
    ) {
        state.value = Downloading()

        val response = bandKit.client.get(url)

        // Bandcamp can often return an HTML file if it smells a bad request
        // What's the solution to this? No idea lol
        if (!response.contentType().isValid()) {
            state.value = InvalidContentType(response.contentType().toString())
            return
        }

        SystemFileSystem.sink(cacheTarget).buffered().use { sink ->
            response.body<Source>().buffered().transferTo(sink)
        }
    }

    private suspend fun exportItem(
        cacheTarget: Path,
        exportTarget: Path
    ) {
        if (item.type != Track) {
            state.value = Extracting

            ZipFile(cacheTarget.toString()).extractAll(exportTarget.toString())

            // Zip4j has messed up permissions on extraction
            exportTarget
                .toFile()
                .listFiles()
                .forEach { file ->
                    file.setReadable(true)
                    file.setWritable(true)
                }
        } else {
            PlatformFile(cacheTarget).copyTo(PlatformFile(exportTarget))
        }
    }

    private fun computeCachePath(encoding: Encoding): Path {
        // Track items will download as an unarchived file, so the correct file extension
        // to download to depends on the codec type
        val fileExtension = when {
            item.type == Track -> encoding.extension
            else -> "zip"
        }

        val fileName = "${item.artist.name} - ${item.title}.$fileExtension"
        return params.cacheDir / fileName
    }

    private fun computeExportPath() = params.exportDir / "${item.artist.name} - ${item.title}"

    fun start(scope: CoroutineScope) {
        job = scope.launch {
            try {
                state.value = FetchingUrl

                val downloadUrl = runCatching { resolveDownloadUrl(item) }
                    .getOrElse {
                        state.value = MissingDownloadUrl(it)
                        return@launch
                    }

                val (encoding, url) = downloadUrl!!

                val cacheTarget = computeCachePath(encoding)
                val exportTarget = computeExportPath()

                if (!PlatformFile(cacheTarget).exists()) {
                    downloadItem(url, cacheTarget)
                }

                exportItem(cacheTarget, exportTarget)

                state.value = Finished(exportTarget)
            } catch (e: CancellationException) {
                state.value = Cancelled
            } catch (e: Throwable) {
                state.value = Error(e)
            }
        }
    }

    fun cancel() {
        job?.cancel()
        if (state.value !is Finished) state.value = Cancelled
    }
}
