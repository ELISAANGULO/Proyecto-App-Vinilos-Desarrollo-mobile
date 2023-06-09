package com.example.mobilevynils.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mobilesvynilis.models.Album
import com.example.mobilesvynilis.models.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.mobilesvynilis.repositories.AlbumRepository
class AlbumViewModel(application: Application, albumId: Int) :  AndroidViewModel(application) {

    private val _album = MutableLiveData<Album>()
    private val AlbumRepository = AlbumRepository(application)
    private val _albumId = albumId


    val album: LiveData<Album>
        get() = _album

    fun addNewTrack(newTrack: Track) {
        val currentAlbum = _album.value
        val currentTracks = currentAlbum?.tracks?.toMutableList()

        currentTracks?.add(newTrack)

        currentAlbum?.let {
            val updatedAlbum = it.copy(tracks = currentTracks)
            _album.value = updatedAlbum
            refreshDataFromNetwork()
        }
    }

    private var _eventNetworkError = MutableLiveData(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

     fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    Log.i("ViewModel","${_albumId}")
                    var data = AlbumRepository.refreshAlbumData(_albumId)
                    _album.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }
    }
    fun refreshData(swipeRefreshLayout: SwipeRefreshLayout){
        refreshDataFromNetwork()
        swipeRefreshLayout.isRefreshing = false
    }




    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application, val albumId:Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            Log.i("ViewModel","Factory ${albumId}")
            if (modelClass.isAssignableFrom(AlbumViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumViewModel(app, albumId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}