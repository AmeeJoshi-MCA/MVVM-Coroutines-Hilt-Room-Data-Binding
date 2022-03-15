package com.myapplication.network

import com.myapplication.network.model.NetworkAlbumDetailList
import com.myapplication.network.model.NetworkAlbumListItem
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumService {

    @GET("/albums")
    suspend fun getAlbumList(): List<NetworkAlbumListItem>

    @GET("/photos/")
    suspend fun getAlbumDetailList(@Query("albumId") albumId: Int): List<NetworkAlbumDetailList>


}