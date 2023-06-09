package com.example.mobilevynils.network

import android.content.Context
import com.example.mobilesvynilis.models.Album
import com.example.mobilesvynilis.models.Artista
import com.example.mobilesvynilis.models.Collector
import com.example.mobilesvynilis.models.Comment
import com.example.mobilesvynilis.models.Track


class CacheManager(context: Context) {
    companion object {
        var instance: CacheManager? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CacheManager(context).also {
                    instance = it
                }
            }
    }

    private var comments: HashMap<Int, List<Comment>> = hashMapOf()
    private var album: HashMap<Int, Album> = hashMapOf()
    private var albums: List<Album> = mutableListOf()
    private var albumes: HashMap<Int, List<Album>> = hashMapOf()
    private var artists: List<Artista> = mutableListOf<Artista>()
    private var artist: HashMap<Int, Artista> = hashMapOf()
    private var collector: HashMap<Int, Collector> = hashMapOf()
    private var collectors: List<Collector> = mutableListOf<Collector>()

    /* Start Album Cache Logic */
    fun addAlbum(albumId: Int, albums: Album) {
        if (!album.containsKey(albumId)) {
            album[albumId] = albums
            println(albums.tracks)
        }

    }

    fun  deleteAlbum()
    {
        album = hashMapOf()
    }


    fun getAlbum(albumId: Int): Album? {
        return if (album[albumId] != null) album[albumId] else null
    }

    fun addAlbums(newalbums: List<Album>) {
        albums = newalbums
    }

    fun getAlbums(): List<Album> {
        return if (albums.isNotEmpty()) albums else mutableListOf()
    }
    /* End Album Cache Logic */
    fun addArtists(newArtists: List<Artista>) {
        if (artists.isEmpty()) {
            artists = newArtists
        }
    }

    fun addArtist(artistId: Int, artists: Artista) {
        if (!artist.containsKey(artistId)) {
            artist[artistId] = artists
        }
    }

    fun getArtists(): List<Artista> {
        return if (artists.isEmpty()) listOf<Artista>() else artists
    }

    fun getArtist(artistId: Int): Artista? {
        return artist[artistId]
    }
    fun deleteAlbums()
    {
        albums = albums.toMutableList()
        (albums as MutableList<Album>).clear()
    }


    fun getCollector(collectorId: Int): Collector? {
        return if (collector[collectorId] != null) collector[collectorId] else null
    }

}