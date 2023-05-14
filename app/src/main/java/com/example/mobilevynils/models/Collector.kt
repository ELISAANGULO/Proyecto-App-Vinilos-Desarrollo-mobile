package com.example.mobilesvynilis.models

data class Collector (
    var collectorId:Int = -1,
    var name:String = "",
    var telephone:String = "",
    var email:String = "",
    var albums: List<Album> = emptyList()
)

