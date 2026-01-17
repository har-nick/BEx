package uk.co.harnick.bex.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState

@Composable
fun ApplicationScope.Splash() {
    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(
            width = 200.dp,
            height = 200.dp,
            position = WindowPosition.Aligned(Alignment.Center)
        ),
        title = "BEx",
        undecorated = true,
        transparent = true,
        resizable = false,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            @Suppress("DEPRECATION")
            Image(
                painter = painterResource("bex_logo.png"),
                contentDescription = null
            )
        }
    }
}
