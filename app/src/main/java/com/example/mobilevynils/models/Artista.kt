package com.example.mobilesvynilis.models

import com.example.mobilevynils.models.Prize
import java.util.Date


data class Artista (
    val artistId: Int,
    val name:String,
    val image:String,
    val description:String,
    val birthDate:String,
    val albums: List<Album>?,
    val prizes: List<Prize>?
)



