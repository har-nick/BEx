package uk.co.harnick.bex.presentation.components.settings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import uk.co.harnick.bex.data.local.SettingsManager
import uk.co.harnick.bex.domain.model.Settings.ThemeMode
import uk.co.harnick.bex.domain.model.Settings.ThemeMode.DARK
import uk.co.harnick.bex.domain.model.Settings.ThemeMode.FOLLOW_SYSTEM
import uk.co.harnick.bex.domain.model.Settings.ThemeMode.LIGHT
import uk.co.harnick.bex.presentation.icons.Contrast
import uk.co.harnick.bex.presentation.icons.Icons
import uk.co.harnick.bex.presentation.state.LocalSettings

@Composable
fun DarkModeSetting() {
    val currentSettings = LocalSettings.current

    fun onUpdateDarkMode(mode: ThemeMode) {
        SettingsManager.updateSettings(
            currentSettings.copy(theme = mode)
        )
    }

    ListItem(
        headlineContent = { Text("Dark Mode") },
        leadingContent = {
            Icon(
                imageVector = Icons.Contrast,
                contentDescription = null
            )
        },
        trailingContent = {
            var expanded by remember { mutableStateOf(false) }

            Column {
                Button(
                    onClick = { expanded = !expanded },
                    modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                    content = {
                        Text(
                            text = when (currentSettings.theme) {
                                FOLLOW_SYSTEM -> "Auto"
                                LIGHT -> "Light"
                                DARK -> "Dark"
                            }
                        )
                    }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Auto") },
                        modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                        onClick = { onUpdateDarkMode(FOLLOW_SYSTEM) }
                    )

                    DropdownMenuItem(
                        text = { Text("Light") },
                        modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                        onClick = { onUpdateDarkMode(LIGHT) }
                    )

                    DropdownMenuItem(
                        text = { Text("Dark") },
                        modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                        onClick = { onUpdateDarkMode(DARK) }
                    )
                }
            }
        }
    )
}
