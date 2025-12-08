package uk.co.harnick.bex.domain.model

import uk.co.harnick.bandkit.library.dto.item.owned.OwnedLibraryItemsResponse

data class LibraryItem(
    val id: Long,
    val type: ItemType,
    val title: String,
    val artist: Artist,
    val artId: Long,
    val gatewayUrl: String,
    val trackList: List<Track>
) {
    enum class ItemType {
        Album, Package, Track;

        companion object {
            fun parse(string: String): ItemType {
                return entries.first {
                    it.toString().lowercase().first() == string.first()
                }
            }
        }
    }
}

fun OwnedLibraryItemsResponse.toLibraryData(): List<LibraryItem> {
    return libraryItems.map {
        val trackListKeyPrefix = it.type.take(1)
        val trackListKey = "$trackListKeyPrefix${it.id}"
        val trackList = trackLists[trackListKey]
            ?.map { dto -> dto.toTrack() }
            ?: throw Exception("Could not find tracklist at key: $trackListKey")

        val downloadUrlKey = "p${it.saleId}"
        val downloadUrl = downloadUrls[downloadUrlKey]
            ?: throw Exception("Could not find download link at key: $downloadUrlKey")

        LibraryItem(
            id = it.id,
            type = LibraryItem.ItemType.parse(it.type),
            title = it.itemTitle,
            artist = Artist(it.artistName, it.artistImageId),
            artId = it.artId,
            gatewayUrl = downloadUrl,
            trackList = trackList
        )
    }
}
