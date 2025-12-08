package uk.co.harnick.bex.data.local

import io.github.vinceglb.filekit.PlatformFile
import java.io.File

actual object MusicDirectory {
    private val home: String = System.getProperty("user.home")
    private val os = System.getProperty("os.name").lowercase()

    private fun nixDefault(): String? {
        val xdgConfig = File(home, ".config/user-dirs.dirs")

        return if (xdgConfig.exists()) {
            val regex = Regex("""XDG_MUSIC_DIR="?([^"\n]+)""")
            regex.find(xdgConfig.readText())
                ?.groupValues?.get(1)
                ?.replace($$"$HOME", home)
        } else null
    }

    actual fun getDefault(): PlatformFile {
        val pathString = when {
            os.contains("win") -> System.getenv("USERPROFILE")?.let { "$it\\Music" }
            os.contains("mac") ->  "$home/Music"
            os.contains("nix") || os.contains("nux") -> nixDefault()
            else -> null
        } ?: "$home/Music"

        return PlatformFile(pathString)
    }
}
