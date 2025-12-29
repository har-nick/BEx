package uk.co.harnick.bex.presentation.components.topbar

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import uk.co.harnick.bex.data.remote.DownloadState
import uk.co.harnick.bex.data.remote.DownloadState.Cancelled
import uk.co.harnick.bex.data.remote.DownloadState.Failed
import uk.co.harnick.bex.data.remote.DownloadState.Finished
import uk.co.harnick.bex.data.remote.DownloadState.InProgress
import uk.co.harnick.bex.data.remote.DownloadState.InProgress.Extracting
import uk.co.harnick.bex.data.remote.DownloadState.Queued
import uk.co.harnick.bex.presentation.icons.Cancel
import uk.co.harnick.bex.presentation.icons.Check
import uk.co.harnick.bex.presentation.icons.Download
import uk.co.harnick.bex.presentation.icons.Downloading
import uk.co.harnick.bex.presentation.icons.FolderOpen
import uk.co.harnick.bex.presentation.icons.FolderZip
import uk.co.harnick.bex.presentation.icons.Icons
import uk.co.harnick.bex.presentation.icons.Replay
import uk.co.harnick.bex.presentation.icons.Warning

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun DownloadState(
    state: DownloadState,
    onCancelDownload: () -> Unit,
    onResumeDownload: () -> Unit
) {
    @Composable
    fun CancelledState() {
        require(state is Cancelled)

        var isHovered by remember { mutableStateOf(false) }

        TooltipArea(
            tooltip = {
                Surface(
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = "Download cancelled.",
                        modifier = Modifier.padding(all = 12.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            },
            delayMillis = 0,
            tooltipPlacement = TooltipPlacement.CursorPoint(
                alignment = Alignment.TopCenter
            )
        ) {
            Box(
                modifier = Modifier
                    .onPointerEvent(
                        eventType = PointerEventType.Enter,
                        onEvent = { isHovered = true }
                    )
                    .onPointerEvent(
                        eventType = PointerEventType.Exit,
                        onEvent = { isHovered = false }
                    ),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = { onResumeDownload() },
                    modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                    content = {
                        IconButton(
                            onClick = { onResumeDownload() },
                            modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                            content = {
                                Crossfade(isHovered) { showResumeIcon ->
                                    Icon(
                                        imageVector = when (showResumeIcon) {
                                            true -> Icons.Replay
                                            false -> Icons.Cancel
                                        },
                                        contentDescription = null
                                    )
                                }
                            }
                        )
                    }
                )
            }
        }
    }

    @Composable
    fun FailedState() {
        require(state is Failed)

        var isHovered by remember { mutableStateOf(false) }

        TooltipArea(
            tooltip = {
                state.reason?.let {
                    Surface(
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text(
                            text = it,
                            modifier = Modifier.padding(all = 12.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            },
            delayMillis = 0,
            tooltipPlacement = TooltipPlacement.CursorPoint(
                alignment = Alignment.TopCenter
            )
        ) {
            Box(
                modifier = Modifier
                    .onPointerEvent(
                        eventType = PointerEventType.Enter,
                        onEvent = { isHovered = true }
                    )
                    .onPointerEvent(
                        eventType = PointerEventType.Exit,
                        onEvent = { isHovered = false }
                    ),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = { onResumeDownload() },
                    modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    ),
                    content = {
                        IconButton(
                            onClick = { onResumeDownload() },
                            modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                            content = {
                                Crossfade(isHovered) { showResumeIcon ->
                                    Icon(
                                        imageVector = when (showResumeIcon) {
                                            true -> Icons.Replay
                                            false -> Icons.Warning
                                        },
                                        contentDescription = null
                                    )
                                }
                            }
                        )
                    }
                )
            }
        }
    }

    @Composable
    fun InProgressState() {
        require(state is InProgress)

        var isHovered by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .onPointerEvent(
                    eventType = PointerEventType.Enter,
                    onEvent = { isHovered = true }
                )
                .onPointerEvent(
                    eventType = PointerEventType.Exit,
                    onEvent = { isHovered = false }
                ),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = { onCancelDownload() },
                modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                content = {
                    Crossfade(isHovered) { showCancelIcon ->
                        Icon(
                            imageVector = if (showCancelIcon) Icons.Cancel else Icons.Download,
                            contentDescription = null
                        )
                    }
                }
            )

            CircularProgressIndicator()
        }
    }

    @Composable
    fun ExtractingState() {
        require(state is Extracting)

        Box(
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.FolderZip,
                contentDescription = null
            )

            CircularProgressIndicator()
        }
    }

    @Composable
    fun FinishedState() {
        require(state is Finished)

        var isHovered by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .onPointerEvent(
                    eventType = PointerEventType.Enter,
                    onEvent = { isHovered = true }
                )
                .onPointerEvent(
                    eventType = PointerEventType.Exit,
                    onEvent = { isHovered = false }
                ),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = { onResumeDownload() },
                modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                content = {
                    Crossfade(isHovered) { showOpenIcon ->
                        Icon(
                            imageVector = if (showOpenIcon) Icons.FolderOpen else Icons.Check,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    }

    @Composable
    fun QueuedState() {
        require(state is Queued)

        IconButton(
            onClick = {},
            content = {
                Icon(
                    imageVector = Icons.Downloading,
                    contentDescription = null
                )
            }
        )
    }

    Row(
        modifier = Modifier
            .fillMaxHeight()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        when (state) {
            is Failed -> FailedState()
            is Cancelled -> CancelledState()
            is Finished -> FinishedState()
            is Extracting -> ExtractingState()
            is InProgress -> InProgressState()
            is Queued -> QueuedState()
        }
    }
}
