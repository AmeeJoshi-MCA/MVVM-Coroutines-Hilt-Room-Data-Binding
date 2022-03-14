package com.myapplication.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumDetail(
    val albumId: Int = 0,
    val id: Int = 0,
    val thumbnailUrl: String = "",
    val title: String = "",
    val url: String = ""
): Parcelable
