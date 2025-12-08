package uk.co.harnick.bex.presentation.components.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import uk.co.harnick.bex.presentation.SettingsPanel
import uk.co.harnick.bex.presentation.SettingsPanel.About
import uk.co.harnick.bex.presentation.SettingsPanel.Advanced
import uk.co.harnick.bex.presentation.SettingsPanel.Display
import uk.co.harnick.bex.presentation.SettingsPanel.Download
import uk.co.harnick.bex.presentation.icons.Download
import uk.co.harnick.bex.presentation.icons.FormatPaint
import uk.co.harnick.bex.presentation.icons.Icons
import uk.co.harnick.bex.presentation.icons.Info
import uk.co.harnick.bex.presentation.icons.InstantMix

@Composable
fun SettingsNavigation(
    currentPanel: SettingsPanel,
    settingsAreDefault: Boolean,
    modifier: Modifier = Modifier,
    onNavigate: (SettingsPanel) -> Unit,
    onResetSettings: () -> Unit
) {
    Surface(
        modifier = Modifier
            .clip(MaterialTheme.shapes.large)
            .then(modifier),
        tonalElevation = LocalAbsoluteTonalElevation.current + 2.dp
    ) {
        Column {
            LazyColumn {
                item {
                    ListItem(
                        headlineContent = { Text("Display") },
                        modifier = Modifier
                            .pointerHoverIcon(PointerIcon.Hand)
                            .clickable { onNavigate(Display) },
                        leadingContent = {
                            Icon(
                                imageVector = Icons.FormatPaint,
                                contentDescription = null
                            )
                        },
                        tonalElevation = if (currentPanel == Display) 2.dp else 0.dp
                    )
                }

                item {
                    ListItem(
                        headlineContent = { Text("Download") },
                        modifier = Modifier
                            .pointerHoverIcon(PointerIcon.Hand)
                            .clickable { onNavigate(Download) },
                        leadingContent = {
                            Icon(
                                imageVector = Icons.Download,
                                contentDescription = null
                            )
                        },
                        tonalElevation = if (currentPanel == Download) 2.dp else 0.dp
                    )
                }

                item {
                    ListItem(
                        headlineContent = { Text("Advanced") },
                        modifier = Modifier
                            .pointerHoverIcon(PointerIcon.Hand)
                            .clickable { onNavigate(Advanced) },
                        leadingContent = {
                            Icon(
                                imageVector = Icons.InstantMix,
                                contentDescription = null
                            )
                        },
                        tonalElevation = if (currentPanel == Advanced) 2.dp else 0.dp
                    )
                }

                item {
                    ListItem(
                        headlineContent = { Text("About") },
                        modifier = Modifier
                            .pointerHoverIcon(PointerIcon.Hand)
                            .clickable { onNavigate(About) },
                        leadingContent = {
                            Icon(
                                imageVector = Icons.Info,
                                contentDescription = null
                            )
                        },
                        tonalElevation = if (currentPanel == About) 2.dp else 0.dp
                    )
                }
            }

            Spacer(
                modifier = Modifier.weight(1F)
            )

            AnimatedVisibility(
                visible = !settingsAreDefault,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                TextButton(
                    onClick = { onResetSettings() },
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .fillMaxWidth()
                        .pointerHoverIcon(PointerIcon.Hand),
                    content = { Text("Reset all settings") }
                )
            }
        }
    }
}
