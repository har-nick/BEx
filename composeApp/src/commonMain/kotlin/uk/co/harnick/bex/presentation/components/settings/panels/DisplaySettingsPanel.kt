package uk.co.harnick.bex.presentation.components.settings.panels

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import uk.co.harnick.bex.presentation.components.settings.components.ThemeSetting

@Composable
fun DisplaySettingsPanel() {

    Surface {
        Column {
            ThemeSetting()
        }
    }
}
