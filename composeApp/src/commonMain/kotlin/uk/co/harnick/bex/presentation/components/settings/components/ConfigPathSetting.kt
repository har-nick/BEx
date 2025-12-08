package uk.co.harnick.bex.presentation.components.settings.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.dialogs.compose.rememberDirectoryPickerLauncher
import io.github.vinceglb.filekit.path
import uk.co.harnick.bex.data.local.SettingsManager
import uk.co.harnick.bex.presentation.icons.Icons
import uk.co.harnick.bex.presentation.icons.Manufacturing
import uk.co.harnick.bex.presentation.state.LocalSettings

@Composable
fun ConfigPathSetting() {
    val currentSettings = LocalSettings.current

    fun onUpdateConfigPath(path: PlatformFile) {
        SettingsManager.updateSettings(
            currentSettings.copy(settingsDir = path)
        )
    }

    ListItem(
        headlineContent = { Text("Config Path") },
        leadingContent = {
            Icon(
                imageVector = Icons.Manufacturing,
                contentDescription = null
            )
        },
        trailingContent = {
            val dirPicker = rememberDirectoryPickerLauncher { newPath ->
                newPath?.let { onUpdateConfigPath(it) }
            }

            Button(
                onClick = { dirPicker.launch() },
                modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                shape = RoundedCornerShape(10.dp),
                content = { Text(LocalSettings.current.settingsDir.path) }
            )
        }
    )
}
