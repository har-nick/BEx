package uk.co.harnick.bex.presentation.components.topbar

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import uk.co.harnick.bandkit.core.BandKit
import uk.co.harnick.bandkit.util.getAlbumImageUrl
import uk.co.harnick.bex.data.remote.DownloadState
import uk.co.harnick.bex.domain.model.LibraryItem

@Composable
fun LazyItemScope.DownloadItem(
    item: LibraryItem,
    state: DownloadState,
    onPauseDownload: (LibraryItem) -> Unit,
    onResumeDownload: (LibraryItem) -> Unit
) {
    ListItem(
        headlineContent = {
            Text(
                text = item.title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        },
        modifier = Modifier.animateItem(),
        leadingContent = {
            AsyncImage(
                model = getAlbumImageUrl(item.artId, BandKit.ImageSize.Small),
                contentDescription = null,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .size(56.dp)
            )
        },
        supportingContent = { Text(item.artist.name) },
        trailingContent = {
            DownloadState(
                state = state,
                onPauseDownload = { onPauseDownload(item) },
                onResumeDownload = { onResumeDownload(item) }
            )
        }
    )
}
