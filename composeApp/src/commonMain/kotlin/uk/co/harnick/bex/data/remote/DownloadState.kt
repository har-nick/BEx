package uk.co.harnick.bex.data.remote

import kotlinx.io.files.Path

sealed interface DownloadState {
    data class Queued(val offset: Long = 0L) : DownloadState

    sealed interface InProgress : DownloadState {
        data object FetchingUrl : InProgress
        data class Downloading(val progress: Float = 0F, val offset: Long = 0) : InProgress
        data object Extracting : InProgress
    }

    data class Finished(val path: Path) : DownloadState
    data object Cancelled : DownloadState

    sealed class Failed(
        val throwable: Throwable?,
        val reason: String? = throwable?.message
    ) : DownloadState {
        data class Error(val error: Throwable) : Failed(
            throwable = error,
            reason = error.message ?: "An unexpected error occurred."
        )

        data class MissingDownloadUrl(val error: Throwable?) : Failed(
            throwable = error,
            reason = "Download URL couldn't be found."
        )

        data class InvalidContentType(private val type: String) : Failed(
            throwable = null,
            reason = "Invalid Content-Type: \"$type\" in download response."
        )
    }

    companion object {
        fun sorted(state: DownloadState): Int = when (state) {
            is InProgress -> 1
            is Queued -> 2
            is Failed -> 3
            is Cancelled -> 4
            is Finished -> 5
        }
    }
}
