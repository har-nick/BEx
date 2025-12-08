package uk.co.harnick.bex.domain.model

import uk.co.harnick.bandkit.library.dto.item.Track as TrackDto

data class Track(
    val id: Long,
    val title: String,
    val artist: String,
    val index: Int,
    val duration: Float
)

fun TrackDto.toTrack() = Track(
    id = id,
    title = title,
    artist = artist,
    index = index ?: 0,
    duration = duration
)
