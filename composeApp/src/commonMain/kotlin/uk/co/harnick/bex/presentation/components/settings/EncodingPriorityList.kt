package uk.co.harnick.bex.presentation.components.settings

import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.rememberReorderableLazyListState
import uk.co.harnick.bandkit.core.BandKit.Encoding
import uk.co.harnick.bex.presentation.icons.DragIndicator
import uk.co.harnick.bex.presentation.icons.Icons

@Composable
fun EncodingPriorityList(
    encodingPriority: List<Pair<Encoding, Boolean>>,
    onUpdate: (List<Pair<Encoding, Boolean>>) -> Unit
) {
    val listState = rememberLazyListState()

    val reorderableState = rememberReorderableLazyListState(listState) { from, to ->
        val newPriority = encodingPriority
            .toMutableList()
            .apply { add(to.index, removeAt(from.index)) }

        onUpdate(newPriority)
    }

    // We need a container that provides constraints for scroll bar to be visible
    Box {
        Row {
            LazyColumn(
                modifier = Modifier.weight(1F),
                state = listState
            ) {
                itemsIndexed(
                    items = encodingPriority,
                    key = { _, item -> item.first }
                ) { index, (encoding, isEnabled) ->
                    ReorderableItem(
                        state = reorderableState,
                        key = encoding
                    ) { _ ->
                        ListItem(
                            headlineContent = { Text(encoding.name) },
                            leadingContent = {
                                Icon(
                                    imageVector = Icons.DragIndicator,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .draggableHandle()
                                        .pointerHoverIcon(PointerIcon.Hand)
                                )
                            },
                            trailingContent = {
                                Switch(
                                    checked = isEnabled,
                                    onCheckedChange = {
                                        val newPriority = encodingPriority
                                            .toMutableList()
                                            .apply {
                                                removeAt(index)
                                                add(index, Pair(encoding, it))
                                            }

                                        onUpdate(newPriority)
                                    },
                                    modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                                )
                            }
                        )
                    }
                }
            }

            VerticalScrollbar(
                adapter = rememberScrollbarAdapter(listState),
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
