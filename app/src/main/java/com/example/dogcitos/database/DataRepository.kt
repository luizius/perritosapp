package com.example.dogcitos.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class DataRepository (private val dao:DogcitoFavDao){

    @WorkerThread
    fun getAll(): Flow<List<DogcitoFav>> {
        return dao.getAll()
    }

    @WorkerThread
    fun insert(persona: DogcitoFav){
        dao.insert(persona)
    }

    @WorkerThread
    fun delete(id: Int){
        dao.delete(id)
    }

}