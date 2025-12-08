package uk.co.harnick.bex.presentation.components.topbar

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import uk.co.harnick.bex.presentation.icons.Download
import uk.co.harnick.bex.presentation.icons.Icons

@Composable
fun DownloadIndicator(
    isVisible: Boolean,
    downloadsActive: Boolean,
    onClick: () -> Unit
) {
    Crossfade(isVisible) { show ->
        if (show) Box(
            contentAlignment = Alignment.Center
        ) {
            Crossfade(downloadsActive) { showIndicator ->
                if (showIndicator) CircularProgressIndicator()
            }

            IconButton(
                onClick = onClick,
                modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                content = {
                    Icon(
                        imageVector = Icons.Download,
                        contentDescription = null
                    )
                }
            )
        }
    }
}
