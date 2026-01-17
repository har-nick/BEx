package uk.co.harnick.bex.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import org.jetbrains.skiko.OS.Linux
import org.jetbrains.skiko.OS.MacOS
import org.jetbrains.skiko.OS.Windows
import org.jetbrains.skiko.hostOs
import uk.co.harnick.bex.App
import uk.co.harnick.bex.domain.model.Settings
import java.awt.Dimension

@Suppress("DEPRECATION")
@Composable
fun ApplicationScope.MainWindow(
    settings: Settings
) {
    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(
            width = 1400.dp,
            height = 800.dp,
            position = WindowPosition.Aligned(Alignment.Center)
        ),
        title = "BEx",
        icon = painterResource(
            "bex_logo." + when (hostOs) {
                Linux -> "png"
                Windows -> "ico"
                MacOS -> "icns"
                else -> throw IllegalStateException("hostOs: \"$hostOs\" is invalid.")
            }
        )
    ) {
        window.minimumSize = Dimension(900, 700)

        App(settings)
    }
}
