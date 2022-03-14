package com.myapplication.network

import com.myapplication.network.model.NetworkAlbumListItem
import retrofit2.http.GET

interface AlbumListService {

    @GET("/albums")
    suspend fun getAlbumList(): List<NetworkAlbumListItem>

}