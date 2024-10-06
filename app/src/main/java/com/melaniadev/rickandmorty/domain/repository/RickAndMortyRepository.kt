package com.melaniadev.rickandmorty.domain.repository

import com.melaniadev.rickandmorty.domain.model.CharacterInfoWrapper
import com.melaniadev.rickandmorty.domain.model.CharacterModel

interface RickAndMortyRepository {
    suspend fun getCharacterList() : CharacterInfoWrapper

    suspend fun getCharacterInfo(characterId: Int) : CharacterModel
}