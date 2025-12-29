package uk.co.harnick.bex.presentation.state

import kotlinx.serialization.Transient
import uk.co.harnick.bex.data.remote.DownloadState
import uk.co.harnick.bex.domain.model.Account
import uk.co.harnick.bex.domain.model.LibraryItem

data class State(
    val isLoggingIn: Boolean = false,
    val isLoadingLibrary: Boolean = false,
    val searchQuery: String = "",
    val downloadsActive: Boolean = false,
    val downloadQueue: Map<LibraryItem, DownloadState> = emptyMap(),
    val settingsPanelVisible: Boolean = false,
    val account: Account? = null,
    val error: Throwable? = null,
    val libraryData: List<LibraryItem>? = null,
) {
    @Transient
    val searchResults: List<LibraryItem> = libraryData
        ?.filter {
            val trackTitles = it.trackList.map { track -> track.title }
            val predicates = listOf(it.artist.name, it.title) + trackTitles
            predicates.any { predicate -> predicate.contains(searchQuery, ignoreCase = true) }
        }
        ?: emptyList()
}
