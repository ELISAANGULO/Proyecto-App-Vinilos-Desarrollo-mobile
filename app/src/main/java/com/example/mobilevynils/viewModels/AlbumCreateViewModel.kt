package com.example.mobilevynils.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.android.volley.VolleyError
import com.example.mobilesvynilis.models.Album
import com.example.mobilesvynilis.repositories.AlbumRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AlbumCreateViewModel(application: Application) :  AndroidViewModel(application) {

    private val albumsRepository = AlbumRepository(application)

    private val _albums = MutableLiveData<List<Album>>()

    val albums: LiveData<List<Album>>
        get() = _albums

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch (Dispatchers.Default){
                withContext(Dispatchers.IO){

                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }

    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    fun crearAlbum(nombreAlbum:String, urlAlbum:String, fechaAlbum:String, generoAlbum:String, empresaAlbum:String, descripcionAlbum:String, callback: (JSONObject)->Unit, onError: (VolleyError)->Unit): String {

        var respuesta = "";
        var album = Album(
            name = nombreAlbum,
            cover = urlAlbum,
            releaseDate = fechaAlbum,
            description = descripcionAlbum,
            genre = generoAlbum,
            recordLabel = empresaAlbum,
            tracks = null,
            performers = null,
            comments = null,

        );
        var gson = Gson()
        var jsonString = gson.toJson(album)


        var dataAlbum = JSONObject(jsonString);
        dataAlbum.remove("albumId")
        dataAlbum.remove("tracks")
        dataAlbum.remove("performers")
        dataAlbum.remove("comments")
        albumsRepository.createAlbum(dataAlbum,callback,onError)

        return respuesta;
    }


    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumCreateViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumCreateViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}