package com.example.mobilevynils.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.mobilesvynilis.models.Album
import com.example.mobilesvynilis.models.Collector
import com.example.mobilesvynilis.repositories.AlbumRepository
import com.example.mobilevynils.repositories.CollectorsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CollectorViewModel(application: Application, collectorId: Int) :  AndroidViewModel(application) {

    private val _collector = MutableLiveData<Collector>()
    private val CollectorRepository = CollectorsRepository(application)
    private val _collectorId = collectorId

    val collector: LiveData<Collector>
        get() = _collector

    private var _eventNetworkError = MutableLiveData(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    Log.i("ViewModel","${_collectorId}")
                    var data = CollectorRepository.refreshCollectorData(_collectorId)
                    _collector.postValue(data)
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

    class Factory(val app: Application, val collectorId:Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            Log.i("ViewModel","Factory ${collectorId}")
            if (modelClass.isAssignableFrom(CollectorViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CollectorViewModel(app, collectorId ) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}