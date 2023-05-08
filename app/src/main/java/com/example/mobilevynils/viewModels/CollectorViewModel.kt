package com.example.mobilevynils.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobilesvynilis.models.Album
import com.example.mobilesvynilis.models.Collector
import com.example.mobilevynils.repositories.CollectorsRepository

class CollectorViewModel(application: Application) :  AndroidViewModel(application) {




    private val collectorsRepository = CollectorsRepository(application)

    private val _collectors = MutableLiveData<List<Collector>>()

    val colectors: LiveData<List<Collector>>
        get() = _collectors

    private var _eventNetworkError = MutableLiveData(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshData()
    }

    private fun refreshData() {

        collectorsRepository.refreshData({
            _collectors.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        }
        )
    }

    /*fun pushData(album:Album) {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    val response = albumsRepository.pushData(album)
                    println("Rpsonse is")
                    println(response)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e: Exception) {
            _eventNetworkError.value = true
        }
    }*/

    fun forceRefreshDataFromNetwork() {
        collectorsRepository.refreshData({
            _collectors.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        }
        )
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CollectorViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CollectorViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")


        }
    }
}
