package com.example.dogcitos.webservice

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

data class DogcitoRandomState(
    val randomDogcito:RandomDogcito =
        RandomDogcito(message = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/Bulldog_inglese.jpg/1200px-Bulldog_inglese.jpg",
            status = "placeholder")
)

data class DogcitoListState(
    val listDogs:ListPhotosDogcito =
        ListPhotosDogcito( message = listOf())
)

data class BreedListState(
    val listDogs:ListBreedDogcito =
        ListBreedDogcito( message = mapOf(), status = "oko")
)


class AppViewModel: ViewModel(){
    private val repository = DogRepository()
    private val scope: CoroutineScope = viewModelScope
    var dogcitoRandomState by mutableStateOf<DogcitoRandomState>(DogcitoRandomState())
        private set
    var listImagesDogcito by mutableStateOf<DogcitoListState>(DogcitoListState())
        private set
    var listBread by mutableStateOf<BreedListState>(BreedListState())
        private set

    fun showRandomDogcito(){
        try {
            scope.launch(Dispatchers.IO){
                val dogcitoRandom=repository.getRandomDogcito()
                dogcitoRandomState = dogcitoRandomState.copy(
                    randomDogcito = dogcitoRandom
                )
            }
        }catch (ex: Exception){
            Log.e("ERROR", ex.message.toString())
        }
    }

    fun getAllDogcitosByBrand(brand: String){
        try {
            scope.launch(Dispatchers.IO){
                val allDogcito=repository.getAllDogcitoByBread(brand.lowercase())
                listImagesDogcito = listImagesDogcito.copy(
                    listDogs = allDogcito
                )
            }
        }catch (ex: Exception){
            Log.e("ERROR", ex.message.toString())
        }
    }

    fun getAllBreedDogcito(){
        try {
            scope.launch(Dispatchers.IO){
                val allBreed=repository.getAllBreads()
                listBread = listBread.copy(
                    listDogs = allBreed
                )
            }
        }catch (ex: Exception){
            Log.e("ERROR", ex.message.toString())
        }
    }



}