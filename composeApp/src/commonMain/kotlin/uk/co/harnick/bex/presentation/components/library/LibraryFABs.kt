package uk.co.harnick.bex.presentation.components.library

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import uk.co.harnick.bex.presentation.icons.Cancel
import uk.co.harnick.bex.presentation.icons.CheckCircle
import uk.co.harnick.bex.presentation.icons.Download
import uk.co.harnick.bex.presentation.icons.Icons

@Composable
fun LibraryFABs(
    hasItemsSelected: Boolean,
    onToggleItems: () -> Unit,
    onStartDownload: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        FloatingActionButton(
            onClick = { onToggleItems() },
            modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)
        ) {
            val icon = if (hasItemsSelected) Icons.Cancel else Icons.CheckCircle

            Icon(
                imageVector = icon,
                contentDescription = null
            )
        }

        FloatingActionButton(
            onClick = { onStartDownload() },
            modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)
        ) {
            Icon(
                imageVector = Icons.Download,
                contentDescription = null
            )
        }
    }
}
