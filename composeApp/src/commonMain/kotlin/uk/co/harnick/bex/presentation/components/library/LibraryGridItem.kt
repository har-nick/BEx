package uk.co.harnick.bex.presentation.components.library

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Hand
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import uk.co.harnick.bandkit.core.BandKit.ImageSize
import uk.co.harnick.bandkit.util.getAlbumImageUrl
import uk.co.harnick.bex.domain.model.LibraryItem

@Composable
fun LibraryGridItem(
    isSelected: Boolean,
    item: LibraryItem,
    onToggle: (LibraryItem) -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(CardDefaults.shape)
            .clickable { onToggle(item) }
            .pointerHoverIcon(Hand)
    ) {
        Card {
            Column(
                modifier = Modifier.padding(all = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                AsyncImage(
                    model = getAlbumImageUrl(item.artId, ImageSize.Medium),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.large)
                        .aspectRatio(1F)
                )

                Text(
                    text = item.title,
                    overflow = Ellipsis,
                    maxLines = 1
                )

                Text(
                    text = item.artist.name,
                    overflow = Ellipsis,
                    maxLines = 1
                )
            }
        }

        Checkbox(
            checked = isSelected,
            onCheckedChange = { onToggle(item) },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 6.dp, end = 6.dp)
        )
    }
}