package com.example.dogcitos.database

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.RuntimeException

data class DBDataState(
    val dogcitosfavs: List<DogcitoFav> = emptyList(),
    val url: String = "",
)

class DBViewModel(context: Context):ViewModel(){
    var favs by mutableStateOf<DBDataState>(DBDataState())
    private val scope =  viewModelScope
    private var repository:DataRepository?=null
    init {
        initRepository(context)
    }

    private fun initRepository(context: Context) {
        val database = DBRoom.getDatabase(context = context)
        repository=DataRepository(database.dogFavDao())
    }

    private fun changeStateDogcitos(listDogcitos: List<DogcitoFav>) {
        favs = favs.copy(
            dogcitosfavs = listDogcitos.sortedWith(compareBy<DogcitoFav>{
                it.id
            }.reversed())
        )
    }

    fun getDogcitos() {
        scope.launch(Dispatchers.IO) {
            repository?.getAll()?.collect{ t->
                changeStateDogcitos(t)
            }
        }
    }

    fun saveDogcito(url:String){
        val existe = favs.dogcitosfavs.find {
            it.url == url
        }
        if(url.isNotEmpty() && existe != null) {
            scope.launch(Dispatchers.IO) {
                repository?.insert(
                    DogcitoFav(url = url)
                )
                getDogcitos()
            }
        }
    }

    fun deleteDogcito(id:Int){
        if(id != null) {
            scope.launch(Dispatchers.IO) {
                repository?.delete(
                    id
                )
                getDogcitos()
            }
        } else {
            throw RuntimeException("Elementos vacios")
        }
    }

}

class DBProviderFactory(private val context: Context): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>) : T {
        return DBViewModel(context = context) as T
    }
}