package com.example.mobilesvynilis.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.example.mobilesvynilis.models.Artista
import com.example.mobilevynils.network.CacheManager
import com.example.mobilevynils.network.NetworkServiceAdapter


class ArtistasRepository (val application: Application){
    suspend fun refreshArtistsData(): List<Artista> {

        val potentialResp = CacheManager.getInstance(application.applicationContext).getArtists()
        return if (potentialResp.isEmpty()) {
            Log.d(" Decision Cache", "get from network")
            val artists = NetworkServiceAdapter.getInstance(application).getArtists()
            CacheManager.getInstance(application.applicationContext).addArtists(artists)
            artists
        } else {
            Log.d(" Decision Cache", "return ${potentialResp.size} elementos para el cache")
            potentialResp
        }
    }

    suspend fun refreshArtistData(musicianId: Int): Artista {
        Log.d("RepositoryArtista","${musicianId}")
        val potentialResp =
            CacheManager.getInstance(application.applicationContext).getArtist(musicianId)
        return if (potentialResp == null) {
            Log.d("Decision Cache", "get from network")
            val artist = NetworkServiceAdapter.getInstance(application).getArtist(musicianId)
            CacheManager.getInstance(application.applicationContext).addArtist(musicianId, artist)
            artist
        } else {
            Log.d("Decision Cache", "return ${potentialResp.name}elementos para el cache")
            potentialResp
        }
    }
}