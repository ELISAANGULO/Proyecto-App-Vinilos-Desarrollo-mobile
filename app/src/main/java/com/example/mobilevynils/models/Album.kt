package com.example.mobilesvynilis.models

import com.example.mobilevynils.models.Performer

data class Album(
    val albumId:Int = -1,
    val name:String,
    val cover:String,
    val releaseDate:String,
    val description:String,
    val genre:String,
    val recordLabel:String,
    val tracks: MutableList<Track>?,
    val performers: List<Performer>?,
    val comments: List<Comment>?
)
