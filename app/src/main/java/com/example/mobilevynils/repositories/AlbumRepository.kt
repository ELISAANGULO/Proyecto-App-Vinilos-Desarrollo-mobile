package com.example.mobilesvynilis.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.example.mobilesvynilis.models.Album
import com.example.mobilevynils.network.CacheManager
import com.example.mobilevynils.network.NetworkServiceAdapter
import org.json.JSONObject
import java.text.SimpleDateFormat

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


    fun createAlbum(datosAlbum: JSONObject, callback: (JSONObject)->Unit, onError: (VolleyError)->Unit): String {

        NetworkServiceAdapter.getInstance(application).postAlbum(datosAlbum,{
            var albums = CacheManager.getInstance(application).getAlbums()
            val parser = SimpleDateFormat("yyyy-MM-dd")
            val formatter = SimpleDateFormat("yyyy")
            val fecha = formatter.format(parser.parse(it.getString("releaseDate")))
            val newList = albums.toMutableList()
            newList.add(Album(albumId = it.getInt("id"),name = it.getString("name"), cover = it.getString("cover"), recordLabel = it.getString("recordLabel"), releaseDate = fecha, genre = it.getString("genre"), description = it.getString("description"), comments = null, performers = null, tracks = null))
            CacheManager.getInstance(application.applicationContext).deleteAlbums()

            CacheManager.getInstance(application.applicationContext).addAlbums(newList)
            callback(it)
        },
            onError
        )

        return "OK";
    }


}