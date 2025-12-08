package uk.co.harnick.bex.presentation.components.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun SettingsPanelContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.large)
            .then(modifier),
    ) {
        CompositionLocalProvider(
            LocalAbsoluteTonalElevation provides 2.dp
        ) {
            content()
        }
    }
}
