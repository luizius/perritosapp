package com.example.dogcitos.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dogcitos.database.DBViewModel
import com.example.dogcitos.ui.shared.CardDogcito
import com.example.dogcitos.ui.shared.Toolbar

@Composable
fun ScreenFavorites(navHostController: NavHostController, bdViewModel: DBViewModel) {
    val favDogcito = bdViewModel.favs
    bdViewModel.getDogcitos()
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Toolbar(navHostController, title = "Dogcitos favoritos")
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Tus Dogcitos favoritos", fontSize = 35.sp,
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 150.dp)) {
                    items(favDogcito.dogcitosfavs) { fav ->
                        CardDogcito(favorito = fav, bdViewModel, true)
                    }
                }
            }
            if (favDogcito.dogcitosfavs.isEmpty()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Aun no cuentas con favoritos",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    ScreenFavorites(navHostController = rememberNavController(), DBViewModel(
        LocalContext.current))
}