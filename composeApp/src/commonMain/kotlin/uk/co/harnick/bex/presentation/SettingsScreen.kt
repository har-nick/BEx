package uk.co.harnick.bex.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import uk.co.harnick.bex.data.local.SettingsManager
import uk.co.harnick.bex.domain.model.Settings
import uk.co.harnick.bex.presentation.SettingsPanel.About
import uk.co.harnick.bex.presentation.SettingsPanel.Advanced
import uk.co.harnick.bex.presentation.SettingsPanel.Display
import uk.co.harnick.bex.presentation.SettingsPanel.Download
import uk.co.harnick.bex.presentation.components.Scrim
import uk.co.harnick.bex.presentation.components.settings.SettingsNavigation
import uk.co.harnick.bex.presentation.components.settings.SettingsPanelContainer
import uk.co.harnick.bex.presentation.components.settings.panels.AboutPanel
import uk.co.harnick.bex.presentation.components.settings.panels.AdvancedSettingsPanel
import uk.co.harnick.bex.presentation.components.settings.panels.DisplaySettingsPanel
import uk.co.harnick.bex.presentation.components.settings.panels.DownloadSettingsPanel
import uk.co.harnick.bex.presentation.icons.Close
import uk.co.harnick.bex.presentation.icons.Icons
import uk.co.harnick.bex.presentation.state.LocalSettings

@Composable
fun SettingsPage(
    onDismiss: () -> Unit,
) {
    var currentPanel by remember { mutableStateOf(Display) }

    Box(
        contentAlignment = Alignment.Center
    ) {
        Scrim(
            modifier = Modifier.clickable(
                onClick = { onDismiss() },
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )
        )

        Surface(
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .fillMaxSize(0.9F)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                SettingsNavigation(
                    currentPanel = currentPanel,
                    settingsAreDefault = LocalSettings.current.settingsAreDefault,
                    modifier = Modifier.weight(0.3F),
                    onNavigate = { currentPanel = it },
                    onResetSettings = { SettingsManager.updateSettings(Settings()) }
                )

                SettingsPanelContainer(
                    modifier = Modifier.weight(0.7F)
                ) {
                    when (currentPanel) {
                        Display -> DisplaySettingsPanel()
                        Download -> DownloadSettingsPanel()
                        Advanced -> AdvancedSettingsPanel()
                        About -> AboutPanel()
                    }
                }

                IconButton(
                    onClick = { onDismiss() },
                    modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                    content = {
                        Icon(
                            imageVector = Icons.Close,
                            contentDescription = null
                        )
                    }
                )
            }
        }
    }
}
