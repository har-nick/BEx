package uk.co.harnick.bex.data.local

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import uk.co.harnick.bex.data.remote.DownloadState.Failed.Cancelled
import uk.co.harnick.bex.data.remote.DownloadState.Finished
import uk.co.harnick.bex.data.remote.DownloadState.Queued
import uk.co.harnick.bex.domain.model.LibraryItem

class DownloadWorkerPool(
    private val allTasks: StateFlow<Map<LibraryItem, DownloadTask>>,
    private val downloadParams: StateFlow<DownloadParams>,
    private val scope: CoroutineScope
) {
    private val activeJobs = mutableSetOf<DownloadTask>()
    private val queue = ArrayDeque<DownloadTask>()

    init {
        scope.launch {
            combine(allTasks, downloadParams) { tasks, params ->
                tasks.values.toList() to params.maxConcurrentDownloads
            }.collect { (tasks, maxConcurrent) ->
                syncQueue(tasks, maxConcurrent)
            }
        }
    }

    fun notifyTaskUpdated() = syncAsync()

    private fun syncAsync() = scope.launch {
        syncQueue(allTasks.value.values.toList(), downloadParams.value.maxConcurrentDownloads)
    }

    private fun syncQueue(tasks: List<DownloadTask>, maxConcurrent: Int) {
        tasks.filter { it.state.value is Queued && it !in queue && it !in activeJobs }
            .forEach(queue::addLast)

        while (activeJobs.size > maxConcurrent) {
            val task = activeJobs.first()
            task.pause()
            activeJobs.remove(task)
            queue.addFirst(task)
        }

        while (activeJobs.size < maxConcurrent && queue.isNotEmpty()) {
            val task = queue.removeFirst()
            activeJobs.add(task)
            task.start(scope)
        }

        activeJobs.removeIf {
            it.state.value is Finished || it.state.value is Cancelled || it.state.value is Error
        }
    }
}
