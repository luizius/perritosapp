package com.example.dogcitos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.dogcitos.database.DBViewModel
import com.example.dogcitos.database.DBProviderFactory
import com.example.dogcitos.webservice.AppViewModel
import com.example.dogcitos.ui.ScreenMain
import com.example.dogcitos.utils.Routes
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.dogcitos.ui.ScreenFavorites
import com.example.dogcitos.ui.ScreenSearch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appModel by viewModels<AppViewModel>()
        val bdViewModel by viewModels<DBViewModel>() {
            DBProviderFactory(this)
        }
        setContent {
            ScreenController(appModel, bdViewModel)
        }
    }
}

//controlado de navegacion de pantallas
@Composable
fun ScreenController(appModel: AppViewModel, bdViewModel: DBViewModel) {
    val navController: NavHostController = rememberNavController()
    //mapeo de rutas y funciones
    NavHost(navController = navController, startDestination = Routes.SreenHome.route ){
        composable(Routes.SreenHome.route) {
            ScreenMain(navHostController = navController, appModel, bdViewModel)
        }
        composable(Routes.SreenFavs.route) {
            ScreenFavorites(navHostController =  navController, bdViewModel)
        }
        composable(Routes.SreenSearch.route) {
            ScreenSearch(navHostController =  navController, appModel, bdViewModel)
        }
    }

}

