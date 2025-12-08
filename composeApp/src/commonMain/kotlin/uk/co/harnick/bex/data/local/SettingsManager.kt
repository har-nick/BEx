package uk.co.harnick.bex.data.local

import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.PlatformFile
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
import kotlinx.coroutines.launch
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
            Json.decodeFromString(path.readString())
        } else Settings()
    }

    private val _settings = MutableStateFlow(Settings())
    val settings: StateFlow<Settings> = _settings

    fun updateSettings(new: Settings) {
        _settings.value = new
    }

    init {
        scope.launch {
            _settings.value = loadInitial()

            settings.collect {
                val activePath = if (it.settingsDir.path.isEmpty()) defaultPath else it.settingsDir
                activePath.writeString(Json.encodeToString(it))

                if (it.settingsDir.path != signpostPath.path) {
                    signpostPath.writeString(activePath.path)
                }
            }
        }
    }
}
