package com.example.dogcitos.ui.shared

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.dogcitos.R
import com.example.dogcitos.database.DBViewModel
import com.example.dogcitos.database.DogcitoFav

@Composable
fun CardDogcito(favorito: DogcitoFav, bdViewModel: DBViewModel, borrar: Boolean= false) {
    val context = LocalContext.current
    Box(modifier = Modifier
        .clip(RectangleShape)
        .padding(10.dp)) {
        AsyncImage(
            model = favorito.url,
            contentDescription = "imagen-perro",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Magenta, CircleShape)
        )
        IconButton(onClick = {
            if(!borrar) {
                bdViewModel.saveDogcito(favorito.url)
                Toast.makeText(context, "Dogcito en favoritos :D", Toast.LENGTH_LONG).show()
            }else {
                bdViewModel.deleteDogcito(favorito.id)
                Toast.makeText(context, "Dogcito eliminado :(", Toast.LENGTH_LONG).show()
            }
        }) {
            if(!borrar) {
                SetIcon( Color.Magenta)
            }else {
                SetIcon( Color.Gray)
            }
        }
    }
}

@Composable
fun SetIcon(color:Color = Color.Red) {
    Icon(
        modifier = Modifier.size(50.dp),
        painter = painterResource(id = R.drawable.heart),
        contentDescription = "Favorites",
        tint = color
    )
}