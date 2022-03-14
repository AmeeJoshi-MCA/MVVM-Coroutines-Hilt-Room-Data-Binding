package com.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.myapplication.database.AlbumDatabase
import com.myapplication.database.asDomainModelDetail
import com.myapplication.domain.AlbumDetail
import com.myapplication.network.AlbumDetailsService
import com.myapplication.network.model.asDatabaseModel
import timber.log.Timber
import javax.inject.Inject

class AlbumDetailsRepository @Inject constructor(
    private val albumDetailsService: AlbumDetailsService,
    private val albumDatabase: AlbumDatabase
) {

    fun getDetails(albumId: Int): LiveData<List<AlbumDetail>> {
        return Transformations.map(albumDatabase.albumDao.getAlbumDetails(albumId)) {
            it.asDomainModelDetail()
        }
    }

    suspend fun refreshAlbumDetailList(albumId: Int) {
        try {
            val albums = albumDetailsService.getAlbumDetailList(albumId)
            albumDatabase.albumDao.insertAlbumDetail(albums.asDatabaseModel())
        } catch (e: Exception) {
            Timber.w(e)
        }
    }
}