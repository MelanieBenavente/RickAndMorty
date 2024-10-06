package com.melaniadev.rickandmorty.data

import com.melaniadev.rickandmorty.data.datasource.local.LocalDataSource
import com.melaniadev.rickandmorty.data.datasource.remote.RemoteDataSource
import com.melaniadev.rickandmorty.data.datasource.remote.common.RetrofitUtils
import com.melaniadev.rickandmorty.domain.model.CharacterInfoWrapper
import com.melaniadev.rickandmorty.domain.model.CharacterModel
import com.melaniadev.rickandmorty.domain.model.mapper.CharacterModelMapper
import com.melaniadev.rickandmorty.domain.repository.RickAndMortyRepository

class RickAndMortyRepositoryImpl : RickAndMortyRepository {
    val localDataSource = LocalDataSource()
    val remoteDataSource = RemoteDataSource()

    override suspend fun getCharacterList(): CharacterInfoWrapper {
        try {
            val apiResponseDto = remoteDataSource.getCharacterListFromRemote()
            val infoWrapperMapped = CharacterModelMapper.toModel(apiResponseDto)

            localDataSource.insertAllData(infoWrapperMapped.characterList)
            return infoWrapperMapped.copy(characterList = localDataSource.getCharacterList())

        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun getCharacterInfo(characterId: Int): CharacterModel {
        try {
            val localCharacter = localDataSource.getCharacterInfo(characterId)
            return localCharacter

        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}

