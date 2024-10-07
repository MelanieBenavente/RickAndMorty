package com.melaniadev.rickandmorty.data.datasource.local

import com.melaniadev.rickandmorty.domain.model.CharacterModel

class LocalDataSource {
    private val characterListCache : MutableList<CharacterModel> = mutableListOf()

    fun insertAllData(characterList : List<CharacterModel>){
        characterListCache.addAll(characterList)
    }
    fun getCharacterList() : List<CharacterModel> {
        return characterListCache
    }
    fun getCharacterInfo(characterId : Int) : CharacterModel {
        val characterFilteredById = characterListCache.first {  it.id == characterId }
        return characterFilteredById
    }
}