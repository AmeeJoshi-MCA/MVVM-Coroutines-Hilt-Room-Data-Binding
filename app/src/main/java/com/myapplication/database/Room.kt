package com.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlbumDao {

    // album List
    @Query("select * from DatabaseAlbumListItem")
    fun getDatabaseAlbum(): LiveData<List<DatabaseAlbumListItem>>

    // album favorite
    @Query("select * from DatabaseAlbumListItem WHERE isFavorite = 1")
    fun getAlbumFavorite(): LiveData<List<DatabaseAlbumListItem>>

    // album detail
    @Query("select * from DatabaseAlbumDetails WHERE albumId LIKE :albumId")
    fun getAlbumDetails(albumId: Int): LiveData<List<DatabaseAlbumDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbumDetail(album: List<DatabaseAlbumDetails>)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAlbum(item: DatabaseAlbumListItem)

    @Update
    suspend fun updateAlbum(item: DatabaseAlbumListItem)

    @Query("select * from DatabaseAlbumListItem where id= :id")
    fun getAlbumByIdOneShot(id: Int): DatabaseAlbumListItem

}

@Database(entities = [DatabaseAlbumListItem::class, DatabaseAlbumDetails::class], version = 1)
abstract class AlbumDatabase : RoomDatabase() {
    abstract val albumDao: AlbumDao
}