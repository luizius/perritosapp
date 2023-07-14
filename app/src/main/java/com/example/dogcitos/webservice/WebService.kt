package com.example.dogcitos.webservice

import com.squareup.moshi.JsonClass
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

val URL_BASE="https://dog.ceo/api/"

@JsonClass(generateAdapter = true)
interface DogcitoRandomeService{
    @GET("breeds/image/random")
    suspend fun getRandomDogcito():RandomDogcito

    @GET("breed/{brand}/images")
    suspend fun getAllDogcitoByBread(@Path("brand") brand: String): ListPhotosDogcito

    @GET("breed/{breed}/images/random")
    suspend fun getRandomDogcitoByBread(@Path("breed") breed: String):RandomDogcito

    @GET("breeds/list/all")
    suspend fun getAllBreads(): ListBreedDogcito
}



object RetrofitInstance{

    private val moshi= Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val service: DogcitoRandomeService by lazy {
        retrofit.create(DogcitoRandomeService::class.java)
    }
}

