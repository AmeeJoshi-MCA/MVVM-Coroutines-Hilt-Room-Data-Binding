package com.myapplication.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiServiceTest : BaseTest() {

    private lateinit var service: AlbumService

    @Before
    fun setup() {
        val url = mockWebServer.url("/")
        service = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                ))
            .build()
            .create(AlbumService::class.java)
    }

    @Test
    fun album_Api_service() {
        enqueue("response_album.json")
        runBlocking {
            val apiResponse = service.getAlbumList()

            Assert.assertNotNull(apiResponse)
            Assert.assertTrue("The list was empty", apiResponse.isNotEmpty())
            Assert.assertEquals("The id's did not match", 1, apiResponse[0].id)
        }
    }

    @Test
    fun album_Detail_Api_service() {
        enqueue("response_album_detail.json")
        runBlocking {
            val apiResponse = service.getAlbumDetailList(1)

            Assert.assertNotNull(apiResponse)
            Assert.assertTrue("The list was empty", apiResponse.isNotEmpty())
            Assert.assertEquals("The id's did not match", 1, apiResponse[0].albumId)
        }
    }

}