package com.myapplication.network.model

import com.myapplication.database.DatabaseAlbumDetails
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkAlbumDetailList(
    @Json(name = "id")
    val id: Int,
    @Json(name = "albumId")
    val albumId: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "thumbnailUrl")
    val thumbnailUrl: String
)

fun List<NetworkAlbumDetailList>.asDatabaseModel(): List<DatabaseAlbumDetails> {
    return map {
        DatabaseAlbumDetails(
            id = it.id,
            albumId = it.albumId,
            title = it.title,
            url = it.url,
            thumbnailUrl = it.thumbnailUrl
        )
    }
}