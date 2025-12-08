package uk.co.harnick.bex.domain.usecase

import uk.co.harnick.bandkit.core.BandKit
import uk.co.harnick.bandkit.library.fetchPrivateLibraryData
import uk.co.harnick.bex.domain.model.LibraryItem
import uk.co.harnick.bex.domain.model.toLibraryData

object GetLibraryItems {
    suspend operator fun invoke(
        bandKit: BandKit,
        userId: Long
    ): List<LibraryItem> {
        val items = bandKit.fetchPrivateLibraryData(userId)
        return items.toLibraryData()
    }
}
