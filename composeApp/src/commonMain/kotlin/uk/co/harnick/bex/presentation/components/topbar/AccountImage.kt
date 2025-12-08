package uk.co.harnick.bex.presentation.components.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import uk.co.harnick.bandkit.core.BandKit
import uk.co.harnick.bandkit.util.getAvatarImageUrl
import uk.co.harnick.bex.presentation.icons.Icons
import uk.co.harnick.bex.presentation.icons.Person

@Composable
fun AccountImage(
    imageId: Long?
) {
    @Composable
    fun Placeholder() {
        Icon(
            imageVector = Icons.Person,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(0.5F),
            tint = MaterialTheme.colorScheme.onBackground
        )
    }

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(52.dp),
        contentAlignment = Alignment.Center
    ) {
        if (imageId == null) Placeholder()
        else AsyncImage(
            model = getAvatarImageUrl(imageId, BandKit.ImageSize.Medium),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}
