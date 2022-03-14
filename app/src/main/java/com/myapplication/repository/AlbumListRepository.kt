package com.myapplication.repository

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.myapplication.database.AlbumDatabase
import com.myapplication.database.DatabaseAlbumListItem
import com.myapplication.database.asDomainModel
import com.myapplication.domain.AlbumItem
import com.myapplication.network.AlbumListService
import com.myapplication.network.model.asDatabaseModel
import timber.log.Timber
import javax.inject.Inject

class AlbumListRepository @Inject constructor(
    private val albumListService: AlbumListService,
    private val albumDatabase: AlbumDatabase
) {

    val albums: LiveData<List<AlbumItem>> =
        Transformations.map(albumDatabase.albumDao.getDatabaseAlbum()) {
            it.asDomainModel()
        }

    val albumFavorite: LiveData<List<AlbumItem>> =
        Transformations.map(albumDatabase.albumDao.getAlbumFavorite()) {
            it.asDomainModel()
        }

    suspend fun refreshAlbumList() {
        try {
            val albumsApi = albumListService.getAlbumList()
            val list : List<DatabaseAlbumListItem> = albumsApi.asDatabaseModel()
            insertAlbumIntoDB(list)
        } catch (e: Exception) {
            Timber.w(e)
        }
    }

    private suspend fun insertAlbumIntoDB(list : List<DatabaseAlbumListItem>){
        for(item in list){
            try{
                albumDatabase.albumDao.insertAlbum(item)
            }catch (exception: SQLiteConstraintException){
                val oldItem = albumDatabase.albumDao.getAlbumByIdOneShot(item.id)
                albumDatabase.albumDao.updateAlbum(item.apply {
                    isFavorite = oldItem.isFavorite
                })
            }catch (throwable: Throwable){
                val oldItem = albumDatabase.albumDao.getAlbumByIdOneShot(item.id)
                albumDatabase.albumDao.updateAlbum(item.apply {
                    isFavorite = oldItem.isFavorite
                })
            }
        }
    }

   suspend fun albumUpdate(album: AlbumItem) {
        try {
            val dataItem = DatabaseAlbumListItem(
                id = album.id,
                title = album.title,
                userId = album.userId,
                isFavorite = album.isFavorite
            )
            albumDatabase.albumDao.updateAlbum(dataItem)
        } catch (e: Exception) {
            Timber.w(e)
        }
    }
}