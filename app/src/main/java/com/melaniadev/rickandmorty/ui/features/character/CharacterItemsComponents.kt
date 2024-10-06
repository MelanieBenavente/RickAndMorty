package com.melaniadev.rickandmorty.ui.features.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.melaniadev.rickandmorty.R
import com.melaniadev.rickandmorty.ui.theme.localCustomColorsPalette

@Composable
fun ImageWithStatusBox(imageUrl: String, characterName: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .padding(15.dp)
                .background(color = localCustomColorsPalette.current.dark_magenta)
                .padding(horizontal = 10.dp)
                .align(Alignment.TopStart)
        ) {
            Text(
                text = characterName,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun InfoElements(
    name: String,
    drawable: Int,
    gender: String,
    species: String,
    origin: String,
    location: String
) {
    Text(
        text = name,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        textAlign = TextAlign.Center,
        color = localCustomColorsPalette.current.orange,
        style = MaterialTheme.typography.titleMedium
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
            Text(
                text = gender,
                modifier = Modifier.padding(start = 8.dp),
                color = Color.White,
                textAlign = TextAlign.Start
            )
        }

        Text(
            modifier = Modifier.padding(end = 16.dp),
            text = species.uppercase(),
            color = Color.White,
            textAlign = TextAlign.End
        )
    }

    Text(
        text = origin,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        textAlign = TextAlign.Left,
        color = Color.White,
        style = MaterialTheme.typography.bodyMedium
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.location_view_svg),
            contentDescription = "Location Icon",
            modifier = Modifier.size(25.dp)
        )

        Text(
            text = stringResource(id = R.string.last_location_txt),
            color = Color.White,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        )
    }
    Text(
        text = location,
        color = Color.White,
        modifier = Modifier
            .padding(start = 8.dp)
    )
}