package com.myapplication.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.myapplication.domain.AlbumDetail

@Entity
data class DatabaseAlbumDetails constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val albumId: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)

fun List<DatabaseAlbumDetails>.asDomainModelDetail(): List<AlbumDetail> {
    return map {
        AlbumDetail(
            albumId = it.albumId,
            thumbnailUrl = it.thumbnailUrl,
            title = it.title,
            url = it.url
        )
    }
}