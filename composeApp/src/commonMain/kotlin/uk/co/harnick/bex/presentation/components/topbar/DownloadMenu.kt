package uk.co.harnick.bex.presentation.components.topbar

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import uk.co.harnick.bex.data.remote.DownloadState
import uk.co.harnick.bex.domain.model.LibraryItem

@Composable
fun DownloadMenu(
    isVisible: Boolean,
    downloadQueue: Map<LibraryItem, DownloadState>,
    onDismiss: () -> Unit,
    onPauseDownload: (LibraryItem) -> Unit,
    onResumeDownload: (LibraryItem) -> Unit,
    onClearDownloads: () -> Unit,
    content: @Composable () -> Unit
) {
    val sortedQueue by remember(downloadQueue) {
        derivedStateOf {
            downloadQueue
                .toList()
                .sortedWith(
                    compareBy<Pair<LibraryItem, DownloadState>>
                    { (_, state) -> DownloadState.sorted(state) }
                        .thenBy { (item, _) -> item.artist.name }
                        .thenBy { (item, _) -> item.title }
                )
        }
    }

    Column {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onDismiss() }
        ) {
            content()
        }

        if (isVisible) {
            Popup(
                offset = IntOffset(x = 0, y = 60),
                onDismissRequest = { onDismiss() }
            ) {
                Surface(
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        TextButton(
                            onClick = onClearDownloads,
                            modifier = Modifier.padding(end = 12.dp),
                            content = { Text("Clear All") }
                        )

                        LazyColumn(
                            modifier = Modifier
                                .widthIn(max = 500.dp)
                                .heightIn(max = 700.dp)
                                .animateContentSize()
                        ) {
                            items(
                                items = sortedQueue,
                                key = { (item, _) -> item.id }
                            ) { (item, state) ->
                                DownloadItem(item, state, onPauseDownload, onResumeDownload)
                            }
                        }
                    }
                }
            }
        }
    }
}
