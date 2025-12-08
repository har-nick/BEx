package uk.co.harnick.bex.presentation

import androidx.compose.material3.SnackbarDuration.Indefinite
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.config
import io.ktor.client.plugins.compression.ContentEncoding
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.AcceptAllCookiesStorage
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.co.harnick.bandkit.core.BandKit
import uk.co.harnick.bex.data.local.DownloadManager
import uk.co.harnick.bex.data.local.SettingsManager
import uk.co.harnick.bex.data.remote.DownloadState.InProgress
import uk.co.harnick.bex.domain.model.LibraryItem
import uk.co.harnick.bex.domain.usecase.GetAccountInfo
import uk.co.harnick.bex.domain.usecase.GetLibraryItems
import uk.co.harnick.bex.presentation.state.Event
import uk.co.harnick.bex.presentation.state.Event.DisplayErrorReportMenu
import uk.co.harnick.bex.presentation.state.Event.DisplayErrorSnackbar
import uk.co.harnick.bex.presentation.state.SnackbarData
import uk.co.harnick.bex.presentation.state.State

class ViewModel(
    state: State = State()
) : ViewModel() {
    private val mutableState = MutableStateFlow(state)
    val state = mutableState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        DisplayErrorSnackbar("An error occurred.", exception)
    }

    //
    // ACCOUNT
    //
    private val tokenFlow = MutableStateFlow<String?>(null)

    fun login(token: String) {
        mutableState.update { it.copy(isLoggingIn = true) }
        tokenFlow.update { token }

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            try {
                val account = GetAccountInfo(bandKit.value)
                mutableState.update { it.copy(account = account) }
            } finally {
                mutableState.update { it.copy(isLoggingIn = false) }
            }
        }
    }

    fun logout() {
        manager.clear()
        mutableState.update { State() }
    }

    //
    // LIBRARY
    //
    private val clientFlow = MutableStateFlow(createNewClient())

    private val bandKit = combine(tokenFlow, clientFlow) { token, client -> BandKit(token, client) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = BandKit(token = null)
        )

    private fun createNewClient(): HttpClient {
        return HttpClient(
            CIO.config {
                requestTimeout = 300000
            }
        ) {
            install(ContentEncoding) {
                deflate()
                gzip()
            }

            install(ContentNegotiation) {
                json()
            }

            install(HttpCookies) {
                storage = AcceptAllCookiesStorage()
            }
        }
    }

    private fun syncClientConfig() = viewModelScope.launch {
        SettingsManager.settings
            .distinctUntilChangedBy { it.maxStreamBandwidthKb to it.maxConcurrentDownloads }
            .collect { clientFlow.update { createNewClient() } }
    }

    fun refreshLibraryData() =
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            mutableState.update {
                it.copy(isLoadingLibrary = true)
            }

            val libraryData = state.value.account
                ?.let { GetLibraryItems(bandKit.value, it.id) }
                ?: emptyList()

            mutableState.update {
                it.copy(libraryData = libraryData, isLoadingLibrary = false)
            }
        }

    fun updateSearchQuery(query: String) = mutableState.update { it.copy(searchQuery = query) }

    //
    // DOWNLOADING
    //
    private val manager = DownloadManager(bandKit)

    fun downloadItem(item: LibraryItem) {
        manager.enqueue(item)
    }

    @OptIn(FlowPreview::class)
    private fun syncDownloadQueue() = viewModelScope.launch {
        manager.allStates
            .debounce(300)
            .collect { states -> mutableState.update { it.copy(downloadQueue = states) } }
    }

    @OptIn(FlowPreview::class)
    private fun syncDownloadsActive() = viewModelScope.launch {
        manager.allStates
            .debounce(300)
            .collect { states ->
                val isActive = states.any { (_, state) -> state is InProgress }
                mutableState.update { it.copy(downloadsActive = isActive) }
            }
    }

    fun resumeDownload(libraryItem: LibraryItem) = manager.enqueue(libraryItem)
    fun pauseDownload(libraryItem: LibraryItem) = manager.pause(libraryItem)
    fun clearQueue() = manager.clear()

    //
    // EVENTS
    //
    private val _snackbar = Channel<SnackbarData>()
    val snackbarFlow = _snackbar.receiveAsFlow()

    private val _errorMenu = Channel<Throwable?>()
    val errorMenuFlow = _errorMenu.receiveAsFlow()

    private fun sendErrorSnackbar(
        errorEvent: DisplayErrorSnackbar
    ) = viewModelScope.launch {
        _snackbar.send(
            SnackbarData(
                message = errorEvent.message,
                withDismissAction = false,
                duration = Indefinite,
                action = SnackbarData.ActionData(
                    label = "Report",
                    onClick = { onEvent(DisplayErrorReportMenu(errorEvent.throwable)) }
                )
            )
        )
    }

    private fun onEvent(event: Event) {
        when (event) {
            is DisplayErrorReportMenu -> viewModelScope.launch { _errorMenu.send(event.throwable) }
            is DisplayErrorSnackbar -> sendErrorSnackbar(event)
        }
    }

    init {
        syncClientConfig()
        syncDownloadQueue()
        syncDownloadsActive()
    }
}
