package uk.co.harnick.bex.presentation.components.library

import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.co.harnick.bex.domain.model.LibraryItem

@Composable
fun LibraryGrid(
    libraryData: List<LibraryItem>,
    selectedItems: List<LibraryItem>,
    onUpdateItems: (List<LibraryItem>) -> Unit
) {
    @Composable
    fun NoItemsNotice() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
            content = { Text("Your library is empty!") }
        )
    }

    fun onToggle(item: LibraryItem) {
        val updatedItems = selectedItems
            .toMutableList()
            .also { mutableList ->
                if (!mutableList.remove(item)) mutableList.add(item)
            }

        onUpdateItems(updatedItems)
    }

    // We need a container that provides constraints for scroll bar to be visible
    Box {
        Row {
            val gridState = rememberLazyGridState()

            LazyVerticalGrid(
                columns = GridCells.Adaptive(180.dp),
                modifier = Modifier.weight(1F),
                state = gridState,
                contentPadding = PaddingValues(all = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (libraryData.isEmpty()) item { NoItemsNotice() }
                else {
                    items(
                        items = libraryData,
                        key = { it.id },
                        itemContent = { item ->
                            val isSelected by remember(selectedItems) {
                                derivedStateOf { selectedItems.contains(item) }
                            }

                            LibraryGridItem(isSelected, item, ::onToggle)
                        }
                    )
                }
            }

            VerticalScrollbar(
                adapter = rememberScrollbarAdapter(gridState),
                modifier = Modifier.padding(top = 8.dp, end = 8.dp, bottom = 8.dp),
                style = LocalScrollbarStyle.current.copy(
                    thickness = 4.dp,
                    hoverColor = MaterialTheme.colorScheme.onSurface,
                    unhoverColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3F)
                )
            )
        }
    }
}
