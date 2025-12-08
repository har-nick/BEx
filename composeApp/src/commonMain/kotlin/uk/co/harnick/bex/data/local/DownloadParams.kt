package uk.co.harnick.bex.data.local

import io.github.vinceglb.filekit.toKotlinxIoPath
import kotlinx.io.files.Path
import uk.co.harnick.bandkit.core.BandKit
import uk.co.harnick.bex.domain.model.Settings

class DownloadParams(
    val maxConcurrentDownloads: Int,
    val cacheDir: Path,
    val encodingPriority: List<BandKit.Encoding>,
    val extractArchive: Boolean,
    val exportDir: Path
)

fun Settings.toDownloadParams() = DownloadParams(
    maxConcurrentDownloads = maxConcurrentDownloads,
    cacheDir = cacheDir.toKotlinxIoPath(),
    encodingPriority = filteredEncodingPriority,
    extractArchive = extractMusicArchives,
    exportDir = exportDir.toKotlinxIoPath()
)
