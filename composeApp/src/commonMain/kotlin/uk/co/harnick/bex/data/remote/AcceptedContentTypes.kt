package uk.co.harnick.bex.data.remote

import io.ktor.http.ContentType
import io.ktor.http.ContentType.Application.Zip
import io.ktor.http.ContentType.Audio.MP4
import io.ktor.http.ContentType.Audio.MPEG
import io.ktor.http.ContentType.Audio.OGG

val AcceptedContentTypes = setOf(
    Zip,
    MP4,
    MPEG,
    OGG,
    ContentType.parse("application/ogg"),
    ContentType.parse("audio/alac"),
    ContentType.parse("audio/flac"),
    ContentType.parse("audio/x-aiff"),
    ContentType.parse("audio/x-wav"),
)
