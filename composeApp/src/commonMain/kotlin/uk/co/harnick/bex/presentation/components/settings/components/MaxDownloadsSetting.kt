package uk.co.harnick.bex.presentation.components.settings.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import uk.co.harnick.bex.data.local.SettingsManager
import uk.co.harnick.bex.presentation.icons.Graph4
import uk.co.harnick.bex.presentation.icons.Icons
import uk.co.harnick.bex.presentation.state.LocalSettings

@Composable
fun MaxDownloadsSetting() {
    val currentSettings = LocalSettings.current

    fun onUpdateMaxConcurrentDownloads(newMax: Int) {
        SettingsManager.updateSettings(
            currentSettings.copy(maxConcurrentDownloads = newMax)
        )
    }

    ListItem(
        headlineContent = { Text("Max. Concurrent Downloads") },
        supportingContent = {
            Text(
                """
                    Sets the maximum number of concurrent downloads.
                    It is recommended to keep this to a low value to avoid rate-limiting.
                """.trimIndent()
            )
        },
        leadingContent = {
            Icon(
                imageVector = Icons.Graph4,
                contentDescription = null,
                modifier = Modifier.rotate(180F)
            )
        },
        trailingContent = {
            OutlinedTextField(
                value = "${LocalSettings.current.maxConcurrentDownloads}",
                onValueChange = {
                    it
                        .take(3)
                        .toIntOrNull()
                        ?.coerceIn(0..100)
                        ?.let { onUpdateMaxConcurrentDownloads(it) }
                        ?: return@OutlinedTextField
                },
                modifier = Modifier.width(80.dp),
                textStyle = MaterialTheme.typography.bodyLarge,
                singleLine = true
            )
        }
    )
}
