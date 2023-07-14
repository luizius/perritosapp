package com.example.dogcitos.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dogcitos.database.DBViewModel
import com.example.dogcitos.database.DogcitoFav
import com.example.dogcitos.ui.shared.CardDogcito
import com.example.dogcitos.webservice.AppViewModel
import com.example.dogcitos.webservice.RandomDogcito
import com.example.dogcitos.utils.Routes

@Composable
fun ScreenMain(navHostController: NavHostController, appModel: AppViewModel, bdViewModel: DBViewModel) {
    val dogcitoRandomState = appModel.dogcitoRandomState
    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            TarjetaPerro(randomDogcito = dogcitoRandomState.randomDogcito, bdViewModel)
            Column(
                Modifier.padding(vertical = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    onClick = { appModel.showRandomDogcito() },
                    colors = ButtonDefaults.buttonColors(Color.Magenta)
                ) {
                    Text(text = "Perrito random")
                }
                Text(text = "Al dar click obtendras un perrito random")
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        navHostController.navigate(Routes.SreenSearch.route)
                    },
                    colors = ButtonDefaults.buttonColors(Color.Blue)
                ) {
                    Icon(
                        Icons.Filled.List,
                        contentDescription = "List",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Text(text = "Ir a lista de perritos")
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Button(
                    onClick = {
                        navHostController.navigate(Routes.SreenFavs.route)
                    },
                    colors = ButtonDefaults.buttonColors(Color.Blue)
                ) {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Favorite",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Text(text = "Ir a favoritos")
                }
            }
        }
    }
}



@Composable
fun TarjetaPerro(randomDogcito: RandomDogcito,bdViewModel: DBViewModel){
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier
            .clip(RectangleShape)
            .padding(10.dp)) {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center ) {
                CardDogcito(DogcitoFav(url = randomDogcito.message), bdViewModel)
            }

        }

    }

}

@Preview
@Composable
fun PreviewMain() {
    ScreenMain(navHostController = rememberNavController(),  appModel = AppViewModel(), DBViewModel(LocalContext.current))
}


