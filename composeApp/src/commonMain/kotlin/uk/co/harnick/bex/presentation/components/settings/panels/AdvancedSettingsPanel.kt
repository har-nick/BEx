package uk.co.harnick.bex.presentation.components.settings.panels

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import uk.co.harnick.bex.presentation.components.settings.components.CachePathSetting
import uk.co.harnick.bex.presentation.components.settings.components.ConfigPathSetting
import uk.co.harnick.bex.presentation.components.settings.components.EncodingPrioritySetting
import uk.co.harnick.bex.presentation.icons.CleaningServices
import uk.co.harnick.bex.presentation.icons.Icons
import uk.co.harnick.bex.presentation.state.LocalSettings

// TODO: Add Max Per-Stream Bandwidth
@Composable
fun AdvancedSettingsPanel() {
    val settings = LocalSettings.current

    Surface {
        Column {
            CachePathSetting()
            ConfigPathSetting()
            EncodingPrioritySetting()

            ListItem(
                headlineContent = { Text("Clear Cache") },
                supportingContent = {
                    Text("Can be useful for fixing stuck or incomplete downloads.")
                },
                leadingContent = {
                    Icon(
                        imageVector = Icons.CleaningServices,
                        contentDescription = null
                    )
                },
                trailingContent = {
                    TextButton(
                        onClick = { settings.cacheDir.file.listFiles().forEach { it.delete() } },
                        content = { Text("Clear Cache") }
                    )
                }
            )
        }
    }
}
