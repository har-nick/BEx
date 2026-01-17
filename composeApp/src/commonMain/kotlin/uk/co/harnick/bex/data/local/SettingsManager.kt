package uk.co.harnick.bex.data.local

import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.delete
import io.github.vinceglb.filekit.div
import io.github.vinceglb.filekit.exists
import io.github.vinceglb.filekit.filesDir
import io.github.vinceglb.filekit.path
import io.github.vinceglb.filekit.readString
import io.github.vinceglb.filekit.writeString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import uk.co.harnick.bex.domain.model.Settings

object SettingsManager {
    private val scope = CoroutineScope(Dispatchers.IO)

    val signpostPath = FileKit.filesDir / "signpost"
    val defaultPath = FileKit.filesDir / "settings.json"

    private suspend fun loadInitial(): Settings {
        val path = if (signpostPath.exists()) {
            PlatformFile(signpostPath.readString())
        } else defaultPath

        return if (path.exists()) {
            try {
                Json.decodeFromString(path.readString())
            } catch (e: SerializationException) {
                e.printStackTrace()
                Settings()
            }
        } else Settings()
    }

    val settings: StateFlow<Settings?>
        field = MutableStateFlow(null)

    fun updateSettings(new: Settings) {
        settings.value = new
    }

    private fun syncWithSignpost() = settings
        .onEach {
            if (it?.settingsDir?.path == signpostPath.path) return@onEach

            val oldSettings =
                if (signpostPath.exists()) PlatformFile(signpostPath.readString()) else null

            val activePath = when {
                (it == null) -> defaultPath
                else -> it.settingsDir
            }

            oldSettings?.delete()
            activePath.writeString(Json.encodeToString(it))
            signpostPath.writeString(activePath.path)
        }.launchIn(scope)

    init {
        scope.launch {
            settings.value = loadInitial()
            syncWithSignpost()
        }
    }
}
