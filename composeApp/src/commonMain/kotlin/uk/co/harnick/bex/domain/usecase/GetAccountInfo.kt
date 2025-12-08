package uk.co.harnick.bex.domain.usecase

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import uk.co.harnick.bandkit.core.BandKit
import uk.co.harnick.bandkit.library.fetchLibrarySummary
import uk.co.harnick.bex.domain.model.Account

object GetAccountInfo {
    private suspend fun fetchAccountHtml(username: String): String {
        return HttpClient().use { client ->
            client.get("${BandKit.BASE_URL}/$username").bodyAsText()
        }
    }

    suspend operator fun invoke(bandKit: BandKit): Account {
        val summary = bandKit.fetchLibrarySummary().summary
        val accountHtml = fetchAccountHtml(summary.ownerUsername)

        val displayName = accountHtml
            .substringAfter("text: name\">")
            .substringBefore("<")

        val avatarId = accountHtml
            .substringAfter("<a class=\"popupImage\"")
            .substringAfter("img/")
            .substringBefore("_")
            .toLongOrNull()

        return Account(
            id = summary.ownerId,
            username = summary.ownerUsername,
            avatarId = avatarId,
            displayName = displayName
        )
    }
}
