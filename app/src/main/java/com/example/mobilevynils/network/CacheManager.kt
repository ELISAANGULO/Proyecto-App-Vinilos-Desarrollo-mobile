package com.example.mobilevynils.network

import android.content.Context
import com.example.mobilesvynilis.models.Album
import com.example.mobilesvynilis.models.Artista
import com.example.mobilesvynilis.models.Collector
import com.example.mobilesvynilis.models.Comment


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
    private var artists: List<Artista> = mutableListOf<Artista>()
    private var artist: HashMap<Int, Artista> = hashMapOf()
    private var collector: HashMap<Int, Collector> = hashMapOf()
    private var collectors: List<Collector> = mutableListOf<Collector>()

    fun addComments(albumId: Int, comment: List<Comment>) {
        if (!comments.containsKey(albumId)) {
            comments[albumId] = comment
        }
    }

    fun getComments(albumId: Int): List<Comment> {
        return if (comments.containsKey(albumId)) comments[albumId]!! else listOf<Comment>()
    }

    /* Start Album Cache Logic */
    fun addAlbum(albumId: Int, albums: Album) {
        if (!album.containsKey(albumId)) {
            album[albumId] = albums
            println(albums.tracks)
        }

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


    fun addCollectors(newCollectors: List<Collector>) {
        if (collectors.isEmpty()) {
            collectors = newCollectors
        }
    }



    fun addCollector(collectorId: Int, collector: Collector, collectors: MutableMap<Int, Collector>) {
        if (!collectors.containsKey(collectorId)) {
            collectors[collectorId] = collector
        }
    }

    fun getCollector(collectorId: Int): Collector? {
        return if (collector[collectorId] != null) collector[collectorId] else null
    }

    fun getCollectors(): List<Collector> {
        return if (collectors.isEmpty()) listOf<Collector>() else collectors
    }
}