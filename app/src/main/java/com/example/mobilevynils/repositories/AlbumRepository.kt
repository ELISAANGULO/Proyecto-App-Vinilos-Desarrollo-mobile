package com.example.mobilevynils.repositories

import android.app.Application
import com.example.mobilesvynilis.models.Album
import com.example.mobilevynils.network.NetworkServiceAdapter

class AlbumRepository (val application: Application){
    suspend fun refreshData(): List<Album> {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getAlbums()
    }
}