package uk.co.harnick.bex.presentation.components.settings.components

import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import uk.co.harnick.bex.data.local.SettingsManager
import uk.co.harnick.bex.presentation.icons.FolderZip
import uk.co.harnick.bex.presentation.icons.Icons
import uk.co.harnick.bex.presentation.state.LocalSettings

@Composable
fun ExtractArchivesSetting() {
    val currentSettings = LocalSettings.current

    fun onToggleExtractArchives(enabled: Boolean) {
        SettingsManager.updateSettings(
            currentSettings.copy(extractMusicArchives = enabled)
        )
    }

    ListItem(
        headlineContent = { Text("Extract Music Archives") },
        supportingContent = {
            Text(
                buildString {
                    append("BEx will ")
                    if (!LocalSettings.current.extractMusicArchives) { append("not ") }
                    append("extract album archives automatically.")
                }
            )
        },
        leadingContent = {
            Icon(
                imageVector = Icons.FolderZip,
                contentDescription = null
            )
        },
        trailingContent = {
            Switch(
                checked = LocalSettings.current.extractMusicArchives,
                onCheckedChange = { onToggleExtractArchives(it) },
                modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)
            )
        }
    )
}
