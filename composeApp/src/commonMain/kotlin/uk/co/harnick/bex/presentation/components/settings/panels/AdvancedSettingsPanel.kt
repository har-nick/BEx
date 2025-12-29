package uk.co.harnick.bex.presentation.components.settings.panels

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import uk.co.harnick.bex.presentation.components.settings.components.CachePathSetting
import uk.co.harnick.bex.presentation.components.settings.components.ConfigPathSetting

// TODO: Add Max Per-Stream Bandwidth, Max Concurrent-Downloads
@Composable
fun AdvancedSettingsPanel() {
    Surface {
        Column {
            CachePathSetting()
            ConfigPathSetting()
        }
    }
}
