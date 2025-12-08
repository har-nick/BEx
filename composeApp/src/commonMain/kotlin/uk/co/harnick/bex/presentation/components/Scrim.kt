package uk.co.harnick.bex.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Scrim(
    alpha: Float = 0.7F,
    allowInput: Boolean = false,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit = {}
) {
    Box(
        modifier = Modifier
            .background(
                Color.Black.copy(alpha)
            )
            .then(
                if (allowInput) return
                else Modifier.onPointerEvent(
                    eventType = PointerEventType.Press,
                    onEvent = {}
                )
            )
            .then(modifier),
        contentAlignment = Alignment.Center,
        content = content
    )
}
