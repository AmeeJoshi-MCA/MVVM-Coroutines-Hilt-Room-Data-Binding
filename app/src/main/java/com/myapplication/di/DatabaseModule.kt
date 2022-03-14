package com.myapplication.di

import android.content.Context
import androidx.room.Room
import com.myapplication.database.AlbumDao
import com.myapplication.database.AlbumDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AlbumDatabase {
        return Room.databaseBuilder(
            appContext,
            AlbumDatabase::class.java,
            "Albums"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideChannelDao(albumsDatabase: AlbumDatabase): AlbumDao {
        return albumsDatabase.albumDao
    }

}