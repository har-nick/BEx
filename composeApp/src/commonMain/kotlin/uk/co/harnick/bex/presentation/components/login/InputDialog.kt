package uk.co.harnick.bex.presentation.components.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Default
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Hand
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation.Companion.None
import androidx.compose.ui.unit.dp
import uk.co.harnick.bex.presentation.icons.Icons
import uk.co.harnick.bex.presentation.icons.NotVisible
import uk.co.harnick.bex.presentation.icons.Visible

@Composable
fun InputDialog(
    tokenInput: String,
    allowInput: Boolean,
    onValueChange: (String) -> Unit,
    onSubmit: (String) -> Unit
) {
    var tokenIsVisible by remember { mutableStateOf(false) }

    @Composable
    fun VisibilityToggle() {
        IconButton(
            onClick = { tokenIsVisible = !tokenIsVisible },
            modifier = Modifier
                .padding(end = 8.dp)
                .pointerHoverIcon(
                    if (allowInput) Hand else Default
                ),
            enabled = allowInput
        ) {
            Icon(
                imageVector = if (tokenIsVisible) Icons.Visible else Icons.NotVisible,
                contentDescription = "Toggle token visibility"
            )
        }
    }

    @Composable
    fun InputField() {
        OutlinedTextField(
            value = tokenInput,
            onValueChange = onValueChange,
            modifier = Modifier
                .sizeIn(minWidth = 150.dp, maxWidth = 600.dp, minHeight = 62.dp)
                .fillMaxWidth()
                .pointerHoverIcon(
                    if (allowInput) Text else Default
                ),
            enabled = allowInput,
            placeholder = { Text("Token") },
            trailingIcon = { VisibilityToggle() },
            visualTransformation = if (tokenIsVisible) None else PasswordVisualTransformation(),
            keyboardActions = KeyboardActions(
                onDone = { onSubmit(tokenInput) }
            ),
            singleLine = true
        )
    }

    @Composable
    fun SubmitTokenButton() {
        Button(
            onClick = { onSubmit(tokenInput) },
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .pointerHoverIcon(
                    if (allowInput) Hand else Default
                ),
            enabled = (allowInput && tokenInput.isNotBlank()),
            content = {
                if (allowInput) Text("Submit")
                else CircularProgressIndicator(
                    modifier = Modifier.size(32.dp),
                    strokeWidth = 3.dp
                )
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        BandcampLogo()
        Text("Please input your Bandcamp token.")
        InputField()
        SubmitTokenButton()
        HelpLink()
    }
}
