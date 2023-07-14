package com.example.dogcitos.webservice

class DogRepository{
    private val dogService = RetrofitInstance.service

    suspend fun getRandomDogcito():RandomDogcito{
        return dogService.getRandomDogcito()
    }
    suspend fun getAllDogcitoByBread(brand: String): ListPhotosDogcito{
        return dogService.getAllDogcitoByBread(brand)
    }
    suspend fun getRandomDogcitoByBread(brand: String):RandomDogcito{
        return dogService.getRandomDogcitoByBread(brand)
    }
    suspend fun getAllBreads():ListBreedDogcito{
        return dogService.getAllBreads()
    }

}