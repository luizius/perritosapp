package com.example.dogcitos.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dogcitos.database.DBViewModel
import com.example.dogcitos.database.DogcitoFav
import com.example.dogcitos.webservice.AppViewModel
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.PopupProperties
import com.example.dogcitos.ui.shared.CardDogcito
import com.example.dogcitos.ui.shared.Toolbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSearch(navHostController: NavHostController, appModel: AppViewModel, bdViewModel: DBViewModel) {
    val context = LocalContext.current
    val listImageState = appModel.listImagesDogcito
    val listBread = appModel.listBread
    var expanded by remember { mutableStateOf(false) }
    var mSelectedText by remember { mutableStateOf("") }
    var listaPerros = arrayListOf<String>()
    listaPerros.add("affenpinscher")
    listaPerros.add("african")
    listaPerros.add("airedale")

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
    appModel.getAllBreedDogcito()
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Toolbar(navHostController, title = "BuscarPerritos")
        //appModel.getAllBreedDogcito()
        Column(
            Modifier.height(110.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Selecciona un Dogcito de la lista",
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )

            OutlinedTextField(
                value = mSelectedText,
                onValueChange = { mSelectedText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .onGloballyPositioned { coordinates ->
                        mTextFieldSize = coordinates.size.toSize()
                    },
                label = {Text("Marcas de perrito")},
                trailingIcon = {
                    Icon(icon,"contentDescription",
                        Modifier.clickable { expanded = !expanded })
                }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
            ) {
                listBread.listDogs.message.forEach { dogcito ->
                    DropdownMenuItem(
                        onClick = {
                            mSelectedText = dogcito.key
                            expanded = false
                            appModel.getAllDogcitosByBrand(dogcito.key)
                        },
                        text = { Text(text = dogcito.key) }
                    )
                }
            }
        }
        /*Button(onClick = { appModel.getAllDogcitosByBrand("african") }) {
            Text(text = "Obtener Perrito por marca ")
        }*/
        if(!listImageState.listDogs.message.isNullOrEmpty()){
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Lista de Dogcito de raza $mSelectedText, dales like!",
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
        Row(
        ) {
            LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 150.dp)) {
                items(listImageState.listDogs.message) { image ->
                    CardDogcito(DogcitoFav(url = image), bdViewModel)
                }
            }
        }
    }
}

@Preview
@Composable
fun ScreenSearch() {
    ScreenSearch(navHostController = rememberNavController(),  appModel = AppViewModel(), DBViewModel(
        LocalContext.current))
}
