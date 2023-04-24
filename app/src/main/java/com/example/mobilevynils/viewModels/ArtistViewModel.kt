package com.example.mobilevynils.viewModels

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.*
import com.example.mobilesvynilis.models.Artista
import com.example.mobilesvynilis.repositories.ArtistasRepository
import java.io.IOException
import java.net.URL

class ArtistViewModel(application: Application) :  AndroidViewModel(application) {
    private val artistasRepository = ArtistasRepository(application)

    private val _artistas = MutableLiveData<List<Artista>>()

    val artistas: LiveData<List<Artista>>
        get() = _artistas

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown
    private var _imageBitmap = MutableLiveData<Bitmap>()
    val imageBitmap: LiveData<Bitmap>
        get() = _imageBitmap
    init {
        refreshDataFromNetwork()
    }



    private fun refreshDataFromNetwork() {
        artistasRepository.refreshData({
            _artistas.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            Log.d("Error", it.toString())
            _eventNetworkError.value = true
        })
    }

    fun loadImageFromUrl(url: String) {
        val thread = Thread(Runnable {
            try {
                val urlConnection = URL(url).openConnection()
                urlConnection.connect()
                val inputStream = urlConnection.getInputStream()
                val bitmap = BitmapFactory.decodeStream(inputStream)
                _imageBitmap.postValue(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        })
        thread.start()
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArtistViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ArtistViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}