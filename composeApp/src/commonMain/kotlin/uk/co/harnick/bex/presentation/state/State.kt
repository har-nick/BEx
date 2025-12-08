package uk.co.harnick.bex.presentation.state

import kotlinx.serialization.Transient
import uk.co.harnick.bex.data.remote.DownloadState
import uk.co.harnick.bex.domain.model.Account
import uk.co.harnick.bex.domain.model.LibraryItem

data class State(
    val error: Throwable? = null,
    val isLoggingIn: Boolean = false,
    val isLoadingLibrary: Boolean = false,
    val searchQuery: String = "",
    val account: Account? = null,
    val downloadsActive: Boolean = false,
    val downloadQueue: Map<LibraryItem, DownloadState> = emptyMap(),
    val libraryData: List<LibraryItem>? = null,
) {
    @Transient
    val searchResults: List<LibraryItem> = libraryData?.filter {
        val predicates = listOf(it.artist.name, it.title) + it.trackList.map { it.title }
        predicates.any { it.contains(searchQuery, ignoreCase = true) }
    } ?: emptyList()
}
