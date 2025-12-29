package uk.co.harnick.bex

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.vinceglb.filekit.path
import okio.Path.Companion.toOkioPath
import org.jetbrains.compose.ui.tooling.preview.Preview
import uk.co.harnick.bex.data.local.SettingsManager
import uk.co.harnick.bex.domain.model.Settings.ThemeMode.DARK
import uk.co.harnick.bex.domain.model.Settings.ThemeMode.FOLLOW_SYSTEM
import uk.co.harnick.bex.domain.model.Settings.ThemeMode.LIGHT
import uk.co.harnick.bex.presentation.LibraryScreen
import uk.co.harnick.bex.presentation.MainScaffold
import uk.co.harnick.bex.presentation.SettingsPage
import uk.co.harnick.bex.presentation.TokenInputScreen
import uk.co.harnick.bex.presentation.ViewModel
import uk.co.harnick.bex.presentation.components.ErrorReportMenu
import uk.co.harnick.bex.presentation.components.setCoilImageLoader
import uk.co.harnick.bex.presentation.components.topbar.TopBar
import uk.co.harnick.bex.presentation.state.LocalAccount
import uk.co.harnick.bex.presentation.state.LocalDarkTheme
import uk.co.harnick.bex.presentation.state.LocalSettings
import uk.co.harnick.bex.theme.AppTheme
import java.io.File

@Preview
@Composable
internal fun App() {
    val settings by SettingsManager.settings.collectAsState()

    val useDarkTheme = when (settings.theme) {
        FOLLOW_SYSTEM -> isSystemInDarkTheme()
        LIGHT -> false
        DARK -> true
    }

    val vm = viewModel { ViewModel() }
    val state by vm.state.collectAsState()

    setCoilImageLoader(File(settings.cacheDir.path.plus("/coil")).toOkioPath())

    AppTheme(useDarkTheme) {
        CompositionLocalProvider(
            LocalAccount provides state.account,
            LocalSettings provides settings,
            LocalDarkTheme provides useDarkTheme
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                MainScaffold(
                    topBar = {
                        TopBar(
                            downloadQueue = state.downloadQueue,
                            searchQuery = state.searchQuery,
                            onUpdateSearchQuery = vm::updateSearchQuery,
                            onToggleSettingsMenu = { vm.toggleSettingsPanel(true) },
                            onLogout = vm::logout,
                            onCancelDownload = vm::cancelItem,
                            onStartDownload = vm::downloadItem,
                            onClearDownloads = vm::clearQueue,
                        )
                    },
                    snackbarData = vm.snackbarFlow
                ) { scaffoldPadding ->
                    Box(
                        modifier = Modifier.padding(scaffoldPadding)
                    ) {
                        when (state.account) {
                            null -> {
                                TokenInputScreen(
                                    allowInput = !state.isLoggingIn,
                                    onSubmit = vm::login
                                )
                            }

                            else -> {
                                LibraryScreen(
                                    state = state,
                                    onRefreshLibrary = vm::refreshLibraryData,
                                    onStartDownload = { items ->
                                        items.forEach { vm.downloadItem(it) }
                                    }
                                )
                            }
                        }
                    }
                }

                if (state.settingsPanelVisible) SettingsPage(
                    onDismiss = { vm.toggleSettingsPanel(false) }
                )

                var errorMenuVisible by remember { mutableStateOf(false) }
                val error by vm.errorMenuFlow.collectAsState(null)

                LaunchedEffect(error) {
                    errorMenuVisible = true
                }

                if (errorMenuVisible && error != null) {
                    ErrorReportMenu(
                        error = error!!,
                        onDismiss = { errorMenuVisible = false }
                    )
                }
            }
        }
    }
}
