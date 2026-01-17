import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.application
import io.github.vinceglb.filekit.FileKit
import kotlinx.coroutines.delay
import uk.co.harnick.bex.data.local.SettingsManager
import uk.co.harnick.bex.presentation.MainWindow
import uk.co.harnick.bex.presentation.Splash

fun main() {
    FileKit.init(appId = "BEx")

    application {
        val settings by SettingsManager.settings.collectAsState()
        var splashDelayElapsed by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            delay(1000L)
            splashDelayElapsed = true
        }

        when {
            (settings != null) && splashDelayElapsed -> MainWindow(settings!!)
            else -> Splash()
        }
    }
}
