package uk.co.harnick.bex.data.local

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import uk.co.harnick.bandkit.core.BandKit
import uk.co.harnick.bex.data.remote.DownloadState
import uk.co.harnick.bex.domain.model.LibraryItem
import uk.co.harnick.bex.domain.model.Settings

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class DownloadManager(
    private val bandKit: StateFlow<BandKit>
) {
    private val scope = CoroutineScope(Dispatchers.IO)

    private val downloadParams = SettingsManager.settings
        .filterNotNull()
        .map { it.toDownloadParams() }
        .stateIn(
            scope = scope,
            started = SharingStarted.Lazily,
            initialValue = Settings().toDownloadParams()
        )

    private val _allTasks = MutableStateFlow<Map<LibraryItem, DownloadTask>>(emptyMap())
    val allTasks: Map<LibraryItem, DownloadTask>
        get() = _allTasks.value

    val allStates: StateFlow<Map<LibraryItem, DownloadState>> = _allTasks
        .map { tasks ->
            tasks.map { (item, task) ->
                task.state.map { item to it }
            }
        }
        .flatMapLatest { stateFlows ->
            if (stateFlows.isEmpty()) flowOf(emptyMap())
            else combine(stateFlows) { pairs -> pairs.toMap() }
        }
        .stateIn(
            scope = scope,
            started = SharingStarted.Eagerly,
            initialValue = emptyMap()
        )

    fun enqueue(item: LibraryItem) {
        val task = DownloadTask(item, bandKit.value, downloadParams.value)
        _allTasks.update { it + (item to task) }
        task.start(scope)
    }

    fun cancel(item: LibraryItem) {
        allTasks[item]?.cancel()
    }

    fun clear() {
        val snapshot = allTasks.toMap()
        snapshot.forEach { (item, _) -> cancel(item) }
        _allTasks.value = emptyMap()
    }
}
