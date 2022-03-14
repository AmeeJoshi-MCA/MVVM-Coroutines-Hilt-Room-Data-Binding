package com.myapplication.domain

data class AlbumItem  (
    val id: Int,
    val title: String,
    val userId: Int,
    var isFavorite: Boolean
)
