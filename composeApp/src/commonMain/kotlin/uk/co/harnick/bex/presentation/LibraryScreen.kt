package uk.co.harnick.bex.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import uk.co.harnick.bex.domain.model.LibraryItem
import uk.co.harnick.bex.presentation.components.library.LibraryFABs
import uk.co.harnick.bex.presentation.components.library.LibraryGrid
import uk.co.harnick.bex.presentation.components.library.LoadingLibraryIndicator
import uk.co.harnick.bex.presentation.state.State

@Composable
fun LibraryScreen(
    state: State,
    onRefreshLibrary: () -> Unit,
    onStartDownload: (List<LibraryItem>) -> Unit
) {
    var selectedItems: List<LibraryItem> by remember { mutableStateOf(emptyList()) }

    LaunchedEffect(Unit) {
        onRefreshLibrary()
    }

    Scaffold(
        floatingActionButton = {
            LibraryFABs(
                hasItemsSelected = (selectedItems == state.libraryData),
                onToggleItems = {
                    selectedItems = when (selectedItems.isEmpty()) {
                        true -> state.libraryData ?: emptyList()
                        false -> emptyList()
                    }
                },
                onStartDownload = { onStartDownload(selectedItems) }
            )
        }
    ) { scaffoldPadding ->
        Box(
            modifier = Modifier.padding(scaffoldPadding)
        ) {
            when {
                state.isLoadingLibrary -> LoadingLibraryIndicator()
                state.libraryData != null -> {
                    LibraryGrid(
                        libraryData = if (state.searchQuery.isNotBlank()) state.searchResults else state.libraryData,
                        selectedItems = selectedItems,
                        onUpdateItems = { selectedItems = it }
                    )
                }
            }
        }
    }
}
