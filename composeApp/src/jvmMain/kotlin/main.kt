import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import io.github.vinceglb.filekit.FileKit
import uk.co.harnick.bex.App
import java.awt.Dimension

fun main() {
    FileKit.init(appId = "BEx")

    application {
        Window(
            title = "BEx",
            state = rememberWindowState(
                width = 1400.dp,
                height = 800.dp,
                position = WindowPosition.Aligned(Alignment.Center)
            ),
            onCloseRequest = ::exitApplication,
        ) {
            window.minimumSize = Dimension(900, 700)

            App()
        }
    }
}
