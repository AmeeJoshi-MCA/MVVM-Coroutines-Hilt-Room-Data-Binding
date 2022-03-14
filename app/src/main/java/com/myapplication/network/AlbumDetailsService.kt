package com.myapplication.network

import com.myapplication.network.model.NetworkAlbumDetailList
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumDetailsService {

    @GET("/photos/")
    suspend fun getAlbumDetailList(@Query("albumId") albumId: Int): List<NetworkAlbumDetailList>

}