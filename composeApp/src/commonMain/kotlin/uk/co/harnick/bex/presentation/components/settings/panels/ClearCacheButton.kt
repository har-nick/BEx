package uk.co.harnick.bex.presentation.components.settings.panels

import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import uk.co.harnick.bex.presentation.icons.CleaningServices
import uk.co.harnick.bex.presentation.icons.Icons
import uk.co.harnick.bex.presentation.state.LocalSettings

@Composable
fun ClearCacheButton() {
    val settings = LocalSettings.current

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
