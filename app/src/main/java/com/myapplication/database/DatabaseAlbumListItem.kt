package com.myapplication.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.myapplication.domain.AlbumItem

@Entity
data class DatabaseAlbumListItem constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val userId: Int,
    var isFavorite:Boolean
)

fun List<DatabaseAlbumListItem>.asDomainModel(): List<AlbumItem> {
    return map {
        AlbumItem(
            id = it.id,
            title = it.title,
            userId = it.userId,
            isFavorite = it.isFavorite
        )
    }
}




