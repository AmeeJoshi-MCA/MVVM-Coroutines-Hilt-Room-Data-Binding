package com.myapplication.views.albumList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.domain.AlbumItem
import com.myapplication.repository.AlbumListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private val albumListRepository: AlbumListRepository
) : ViewModel() {

    val data = albumListRepository.albums
    val dataFavorite = albumListRepository.albumFavorite

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllAlbum()
        }
    }

   private suspend fun getAllAlbum(){
        viewModelScope.launch(Dispatchers.IO) {
            albumListRepository.refreshAlbumList()
        }
    }

    fun insertAlbumForFavorite(album: AlbumItem) = viewModelScope.launch(Dispatchers.IO) {
        albumListRepository.albumUpdate(album)
    }

}