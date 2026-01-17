package uk.co.harnick.bex.presentation.components.settings.panels

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import uk.co.harnick.bex.presentation.components.settings.components.EncodingPrioritySetting
import uk.co.harnick.bex.presentation.components.settings.components.ExportPathSetting

@Composable
fun DownloadSettingsPanel() {
    Surface {
        Column {
            ExportPathSetting()
            EncodingPrioritySetting()
//            MaxDownloadsSetting()
        }
    }
}
