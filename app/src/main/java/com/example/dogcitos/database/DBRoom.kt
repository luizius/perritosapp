package com.example.dogcitos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DogcitoFav::class], version = 1)
abstract class DBRoom: RoomDatabase() {
    abstract fun dogFavDao(): DogcitoFavDao

    companion object {
        @Volatile
        private var INSTANCE: DBRoom?=null

        fun getDatabase(context: Context):DBRoom {
            return INSTANCE?: synchronized(this){
                Room.databaseBuilder(context, DBRoom::class.java, "prueba.db")
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}