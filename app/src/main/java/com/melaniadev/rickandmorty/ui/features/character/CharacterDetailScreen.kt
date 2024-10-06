package com.melaniadev.rickandmorty.ui.features.character

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.melaniadev.rickandmorty.domain.model.CharacterModel
import com.melaniadev.rickandmorty.domain.model.Gender
import com.melaniadev.rickandmorty.ui.common.extensions.getGenderIconResource
import com.melaniadev.rickandmorty.ui.features.character.viewmodel.CharacterDetailViewModel
import com.melaniadev.rickandmorty.ui.theme.localCustomColorsPalette

@Composable
fun CharacterDetailView(navigationController: NavHostController, id: Int) {
    val characterDetailViewModel: CharacterDetailViewModel = hiltViewModel()
    val characterState = characterDetailViewModel.characterModelState.collectAsState()
    characterDetailViewModel.getCharacterDetail(id)

    characterState.value?.let { CharacterDetailCard(character = it, gender = it.gender) }
}

@Composable
private fun CharacterDetailCard(character: CharacterModel, gender: Gender) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = localCustomColorsPalette.current.magenta)
            .padding(35.dp)
    ) {
        Card(
            elevation = CardDefaults.cardElevation(10.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = localCustomColorsPalette.current.blue_green)
                    .border(
                        width = 10.dp,
                        color = localCustomColorsPalette.current.light_green,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp)
            ) {
                ImageWithStatusBox(
                    imageUrl = character.image,
                    characterName = character.status.statusName
                )
                InfoElements(
                    name = character.name,
                    drawable = gender.getGenderIconResource(),
                    gender = gender.genderName,
                    location = character.location,
                    origin = character.origin,
                    species = character.species
                )
            }
        }
    }
}