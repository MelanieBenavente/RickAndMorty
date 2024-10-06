package com.melaniadev.rickandmorty.ui.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.melaniadev.rickandmorty.R
import com.melaniadev.rickandmorty.domain.model.CharacterModel
import com.melaniadev.rickandmorty.domain.model.Gender
import com.melaniadev.rickandmorty.domain.model.Status
import com.melaniadev.rickandmorty.ui.common.NavigationRoute
import com.melaniadev.rickandmorty.ui.common.components.ImageIconAndTextComponent
import com.melaniadev.rickandmorty.ui.common.extensions.getGenderIconResource
import com.melaniadev.rickandmorty.ui.common.extensions.getStatusIconResource
import com.melaniadev.rickandmorty.ui.theme.localCustomColorsPalette


@Composable
fun TopBarComponent() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize()
        .padding(vertical = 20.dp)
        .background(Color.Transparent)) {

        Image(painter = painterResource(id = R.drawable.rick_and_morty_title_svg), contentDescription = null, modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun CharacterItemComponent(character: CharacterModel, navigationController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .wrapContentSize()
            .clickable { navigationController.navigate(NavigationRoute.CHARACTER_DETAIL.name + "/${character.id}") }
            .padding(10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = localCustomColorsPalette.current.blue_green)
            .border(
                width = 5.dp,
                color = localCustomColorsPalette.current.light_green,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
        ) {

            CharacterInfoComponent(
                characterName = character.name,
                gender = character.gender,
                status = character.status,
                modifier = Modifier
                    .weight(0.6f)
                    .padding(15.dp)
            )

            Spacer(modifier = Modifier.width(1.dp))

            AsyncImage(
                contentScale = ContentScale.Crop,
                model = character.image,
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .size(150.dp)
                    .weight(0.4f)
            )
        }
    }
}

@Composable
fun CharacterInfoComponent(
    modifier: Modifier? = null,
    characterName: String,
    gender: Gender,
    status: Status
) {
    Column(
        modifier = modifier ?: Modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = characterName,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            color = localCustomColorsPalette.current.orange
        )

        Spacer(modifier = Modifier.size(8.dp))

        gender?.let {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.Start)
            ) {
                ImageIconAndTextComponent(text = gender.genderName, gender.getGenderIconResource())
            }

            Spacer(modifier = Modifier.size(8.dp))

            status?.let {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.Start)
                ) {
                    ImageIconAndTextComponent(text = status.statusName, status.getStatusIconResource())
                }
            }
        }
    }
}