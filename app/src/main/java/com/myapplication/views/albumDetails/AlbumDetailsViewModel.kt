package com.myapplication.views.albumDetails

import androidx.databinding.ObservableParcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.domain.AlbumDetail
import com.myapplication.repository.AlbumDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    private val albumDetailsRepository: AlbumDetailsRepository
) : ViewModel() {

    val albumDetails = ObservableParcelable(AlbumDetail())

    fun getDetails(albumId: Int) = albumDetailsRepository.getDetails(albumId)

    fun refreshDetails(albumId: Int) = viewModelScope.launch(Dispatchers.IO) {
        albumDetailsRepository.refreshAlbumDetailList(albumId)
    }

}