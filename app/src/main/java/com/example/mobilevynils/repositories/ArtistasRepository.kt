package com.example.mobilesvynilis.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.mobilesvynilis.models.Artista
import com.example.mobilevynils.network.NetworkServiceAdapter


class ArtistasRepository (val application: Application){
    fun refreshData(callback: (List<Artista>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getArtistas({
            //Guardar los artistas de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }

}