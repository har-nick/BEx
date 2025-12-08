package uk.co.harnick.bex.presentation.components.settings.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import uk.co.harnick.bandkit.core.BandKit
import uk.co.harnick.bex.data.local.SettingsManager
import uk.co.harnick.bex.presentation.components.settings.EncodingPriorityList
import uk.co.harnick.bex.presentation.icons.ArrowDropDown
import uk.co.harnick.bex.presentation.icons.Icons
import uk.co.harnick.bex.presentation.state.LocalSettings

@Composable
fun EncodingPrioritySetting() {
    val currentSettings = LocalSettings.current

    fun onUpdateEncodingPriority(priority: List<Pair<BandKit.Encoding, Boolean>>) {
        SettingsManager.updateSettings(
            currentSettings.copy(encodingPriority = priority)
        )
    }

    Column(
        modifier = Modifier.animateContentSize()
    ) {
        var isExpanded by remember { mutableStateOf(false) }

        ListItem(
            headlineContent = { Text("Encoding Priority") },
            supportingContent = {
                Text(
                    "BEx will attempt to download files in descending order. Disabled codecs will be ignored entirely."
                )
            },
            modifier = Modifier
                .pointerHoverIcon(PointerIcon.Hand)
                .clickable { isExpanded = !isExpanded },
            trailingContent = {
                Icon(
                    imageVector = Icons.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.rotate(if (isExpanded) 180F else 0F)
                )
            }
        )

        if (isExpanded) EncodingPriorityList(
            encodingPriority = LocalSettings.current.encodingPriority,
            onUpdate = { onUpdateEncodingPriority(it) }
        )
    }
}
