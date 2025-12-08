package uk.co.harnick.bex.domain.model

import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.cacheDir
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import uk.co.harnick.bandkit.core.BandKit
import uk.co.harnick.bandkit.core.BandKit.Encoding.AAC
import uk.co.harnick.bandkit.core.BandKit.Encoding.AIFF
import uk.co.harnick.bandkit.core.BandKit.Encoding.ALAC
import uk.co.harnick.bandkit.core.BandKit.Encoding.FLAC
import uk.co.harnick.bandkit.core.BandKit.Encoding.MP3_320
import uk.co.harnick.bandkit.core.BandKit.Encoding.MP3_V0
import uk.co.harnick.bandkit.core.BandKit.Encoding.OGG
import uk.co.harnick.bandkit.core.BandKit.Encoding.WAV
import uk.co.harnick.bex.data.local.MusicDirectory
import uk.co.harnick.bex.data.local.SettingsManager
import uk.co.harnick.bex.util.PlatformFileSerializer

@Serializable
data class Settings(
    val theme: ThemeMode = ThemeMode.FOLLOW_SYSTEM,

    @Serializable(with = PlatformFileSerializer::class)
    val cacheDir: PlatformFile = FileKit.cacheDir,

    @Serializable(with = PlatformFileSerializer::class)
    val settingsDir: PlatformFile = SettingsManager.defaultPath,

    @Serializable(with = PlatformFileSerializer::class)
    val exportDir: PlatformFile = MusicDirectory.getDefault(),

    val extractMusicArchives: Boolean = true,

    val encodingPriority: List<Pair<BandKit.Encoding, Boolean>> = listOf(
        FLAC to true,
        ALAC to true,
        WAV to true,
        AAC to true,
        AIFF to true,
        OGG to true,
        MP3_320 to true,
        MP3_V0 to true
    ),

    val maxConcurrentDownloads: Int = 3,
    val maxStreamBandwidthKb: Int = 10000
) {
    enum class ThemeMode { FOLLOW_SYSTEM, LIGHT, DARK }

    @Transient
    val filteredEncodingPriority = encodingPriority
        .filter { it.second }
        .map { it.first }

    val settingsAreDefault: Boolean
        get() = this == Settings()
}
