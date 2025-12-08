package uk.co.harnick.bex.data.local

import io.github.vinceglb.filekit.PlatformFile

expect object MusicDirectory {
    fun getDefault(): PlatformFile
}
