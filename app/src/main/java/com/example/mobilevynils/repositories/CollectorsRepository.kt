package com.example.mobilevynils.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.mobilesvynilis.models.Artista
import com.example.mobilesvynilis.models.Collector
import com.example.mobilevynils.network.NetworkServiceAdapter

class CollectorsRepository (val application: Application){
    fun refreshData(callback: (List<Collector>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getCollectors({
            //Guardar los artistas de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }

}