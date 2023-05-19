package com.example.mobilevynils.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.example.mobilesvynilis.models.Album
import com.example.mobilesvynilis.models.Artista
import com.example.mobilesvynilis.models.Collector
import com.example.mobilevynils.network.CacheManager
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
    suspend fun refreshCollectorData(collectorId: Int): Collector {
        val potentialResp =
            CacheManager.getInstance(application.applicationContext).getCollector(collectorId)
        return if (potentialResp == null) {
            Log.d("Cache decision", "get from network ${collectorId}")
            val collector = NetworkServiceAdapter.getInstance(application).getCollector(collectorId)
            //CacheManager.getInstance(application.applicationContext).addCollector(collectorId, collector)
            collector
        } else {
            Log.d("Cache decision", "return ${potentialResp.collectorId} from cache")
            potentialResp
        }
    }

}