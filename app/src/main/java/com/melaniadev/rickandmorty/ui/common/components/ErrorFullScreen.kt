package com.melaniadev.rickandmorty.ui.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.navigation.NavController
import com.melaniadev.rickandmorty.R
import com.melaniadev.rickandmorty.ui.theme.localCustomColorsPalette

@Composable
fun ErrorFullScreenView(globalNavigator: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 80.dp),
            text = stringResource(R.string.something_went_wrong),
            color = localCustomColorsPalette.current.blue_green,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center

        )
        Image(
            painter = painterResource(R.drawable.blockerror_img),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 25.dp)
                .weight(1f),
            contentScale = ContentScale.Fit
        )
        Button(
            onClick = { globalNavigator.popBackStack() },
            modifier = Modifier
                .padding(bottom = 20.dp, start = 15.dp, end = 15.dp)
                .fillMaxWidth()
                .background(color = localCustomColorsPalette.current.dark_green),
            shape = RoundedCornerShape(8.dp)
        ){
            Text(text = stringResource(R.string.back), style = MaterialTheme.typography.bodyMedium)
        }
    }
}