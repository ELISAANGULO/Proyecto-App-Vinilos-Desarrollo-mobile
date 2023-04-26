package com.example.mobilevynils.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.mobilesvynilis.models.Album
import com.example.mobilevynils.network.NetworkServiceAdapter



class AlbumRepository (val application: Application){
    fun refreshData(callback: (List<Album>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getAlbums({
            //Guardar los Albums de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }

}