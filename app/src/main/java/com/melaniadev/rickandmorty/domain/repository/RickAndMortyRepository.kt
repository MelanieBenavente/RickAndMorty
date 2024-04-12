package com.melaniadev.rickandmorty.domain.repository

import com.melaniadev.rickandmorty.domain.model.CharacterInfoWrapper

interface RickAndMortyRepository {
    suspend fun getCharacterList() : CharacterInfoWrapper
}