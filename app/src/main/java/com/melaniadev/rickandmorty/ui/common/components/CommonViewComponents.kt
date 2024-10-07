package com.melaniadev.rickandmorty.ui.common.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.melaniadev.rickandmorty.ui.theme.localCustomColorsPalette

@Composable
fun ImageIconAndTextComponent(text: String, @DrawableRes drawable: Int) {
    Image(
        painter = painterResource(id = drawable),
        contentDescription = null,
        modifier = Modifier.size(25.dp),
    )
    Spacer(modifier = Modifier.width(8.dp))
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        color = localCustomColorsPalette.current.white
    )
}