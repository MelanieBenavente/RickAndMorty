package com.melaniadev.rickandmorty.domain.repository

import com.melaniadev.rickandmorty.domain.model.CharacterModel

interface RickAndMortyRepository {
    suspend fun getCharacterList() : List<CharacterModel>
}