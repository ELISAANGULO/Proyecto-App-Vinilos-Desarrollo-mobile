package com.example.mobilesvynilis.repositories

import android.app.Application
import android.util.Log
import com.example.mobilesvynilis.models.Album
import com.example.mobilevynils.network.CacheManager
import com.example.mobilevynils.network.NetworkServiceAdapter

class AlbumRepository (val application: Application){
    suspend fun refreshData(): List<Album> {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getAlbums()
    }
    suspend fun refreshAlbumData(albumId: Int): Album {
        val potentialResp =
            CacheManager.getInstance(application.applicationContext).getAlbum(albumId)
        return if (potentialResp == null) {
            Log.d("Cache decision", "get from network ${albumId}")
            val album = NetworkServiceAdapter.getInstance(application).getAlbum(albumId)
            CacheManager.getInstance(application.applicationContext).addAlbum(albumId, album)
            album
        } else {
            Log.d("Cache decision", "return ${potentialResp.albumId} from cache")
            potentialResp
        }
    }
}