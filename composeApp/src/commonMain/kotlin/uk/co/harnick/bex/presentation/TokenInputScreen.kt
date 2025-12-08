package uk.co.harnick.bex.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import uk.co.harnick.bex.presentation.components.Scrim
import uk.co.harnick.bex.presentation.components.login.InputDialog

@Composable
fun TokenInputScreen(
    allowInput: Boolean,
    onSubmit: (String) -> Unit
) {
    var tokenInput by remember { mutableStateOf("") }

    Scrim(
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .sizeIn(maxWidth = 700.dp)
                .align(Center)
        ) {
            InputDialog(
                tokenInput = tokenInput,
                allowInput = allowInput,
                onValueChange = { tokenInput = it },
                onSubmit = onSubmit,
            )
        }
    }
}
