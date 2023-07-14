package com.example.dogcitos.utils

sealed class Routes(var route: String) {
    object SreenHome: Routes("screen_home")
    object SreenFavs: Routes("screen_favs")
    object SreenSearch: Routes("screen_search")
}