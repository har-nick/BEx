package uk.co.harnick.bex.data.remote

import io.ktor.http.ContentType

fun ContentType?.isValid() = this in AcceptedContentTypes
