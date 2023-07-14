package com.example.dogcitos.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface  DogcitoFavDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dogfav: DogcitoFav)

    @Query("SELECT * FROM dog_favs")
    fun getAll(): Flow<List<DogcitoFav>>

    @Query("DELETE FROM DOG_FAVS WHERE id = :id")
    fun delete(id:Int)


}