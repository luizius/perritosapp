package com.example.dogcitos.webservice

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RandomDogcito(
    @field:Json(name="message") val message:String,
    @field:Json(name="status") val status:String,
)

@JsonClass(generateAdapter = true)
data class ListPhotosDogcito(
    @field:Json(name="message") val message:List<String>,
)


@JsonClass(generateAdapter = true)
data class ListBreedDogcito(
    @field:Json(name="message") val message:Map<String, List<String>>,
    @field:Json(name="status") val status:String
)

@JsonClass(generateAdapter = true)
data class Breed(
   val breed: Map<String, List<String>>
)
