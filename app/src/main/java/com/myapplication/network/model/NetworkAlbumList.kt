package com.myapplication.network.model

import com.myapplication.database.DatabaseAlbumListItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkAlbumListItem(
    @Json(name = "id")
    val id: Int,
    @Json(name = "userId")
    val userId: Int,
    @Json(name = "title")
    val title: String
)


fun List<NetworkAlbumListItem>.asDatabaseModel(): List<DatabaseAlbumListItem> {
    return map {
        DatabaseAlbumListItem(
            id = it.id,
            userId = it.userId,
            title = it.title,
            isFavorite = false
        )
    }
}