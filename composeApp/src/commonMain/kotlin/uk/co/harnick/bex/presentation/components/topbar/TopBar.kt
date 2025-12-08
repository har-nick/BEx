package uk.co.harnick.bex.presentation.components.topbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import uk.co.harnick.bex.data.remote.DownloadState
import uk.co.harnick.bex.data.remote.DownloadState.InProgress
import uk.co.harnick.bex.domain.model.LibraryItem
import uk.co.harnick.bex.presentation.components.Scrim
import uk.co.harnick.bex.presentation.state.LocalAccount

@Composable
fun TopBar(
    downloadQueue: Map<LibraryItem, DownloadState>,
    searchQuery: String,
    onUpdateSearchQuery: (String) -> Unit,
    onToggleSettingsMenu: () -> Unit,
    onLogout: () -> Unit,
    onPauseDownload: (LibraryItem) -> Unit,
    onResumeDownload: (LibraryItem) -> Unit,
    onClearDownloads: () -> Unit,
) {
    val account = LocalAccount.current
    val isEnabled by remember(account) { mutableStateOf(account != null) }
    val downloadsActive by remember(downloadQueue) {
        derivedStateOf { downloadQueue.any { (_, state) -> state is InProgress } }
    }

    // Can't use IntrinsicSizing to clamp Scrim with DownloadMenu's LazyColumn
    var barSize by remember { mutableStateOf(IntSize.Zero) }

    Box(
        modifier = Modifier.onGloballyPositioned { barSize = it.size }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            SearchBar(
                query = searchQuery,
                onUpdateQuery = { onUpdateSearchQuery(it) },
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(0.5F)
            )

            Row(
                modifier = Modifier.align(Alignment.CenterEnd),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                var downloadMenuVisible by remember { mutableStateOf(false) }

                DownloadMenu(
                    downloadQueue = downloadQueue,
                    isVisible = downloadMenuVisible,
                    onDismiss = { downloadMenuVisible = false },
                    onPauseDownload = onPauseDownload,
                    onResumeDownload = onResumeDownload,
                    onClearDownloads = {
                        downloadMenuVisible = false
                        onClearDownloads()
                    },
                ) {
                    DownloadIndicator(
                        isVisible = downloadQueue.isNotEmpty(),
                        downloadsActive = downloadsActive,
                        onClick = { downloadMenuVisible = !downloadMenuVisible }
                    )
                }

                AccountMenu(
                    onToggleSettingsMenu = onToggleSettingsMenu,
                    onLogout = onLogout
                ) {
                    AccountImage(account?.avatarId)
                }
            }
        }

        if (!isEnabled) Scrim(
            modifier = Modifier.size(
                width = with(LocalDensity.current) { barSize.width.toDp() },
                height = with(LocalDensity.current) { barSize.height.toDp() }
            )
        )
    }
}
