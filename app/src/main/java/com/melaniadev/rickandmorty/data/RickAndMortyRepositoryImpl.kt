package com.melaniadev.rickandmorty.data

import com.melaniadev.rickandmorty.data.common.RetrofitUtils
import com.melaniadev.rickandmorty.domain.model.CharacterInfoWrapper
import com.melaniadev.rickandmorty.domain.model.CharacterModel
import com.melaniadev.rickandmorty.domain.model.mapper.CharacterModelMapper
import com.melaniadev.rickandmorty.domain.repository.RickAndMortyRepository

class RickAndMortyRepositoryImpl : RickAndMortyRepository {
    var numberOfPage: Int = 1
    val characterListCache : MutableList<CharacterModel> = mutableListOf()
    override suspend fun getCharacterList(): CharacterInfoWrapper {

        try {
            val callResponse = RetrofitUtils.getRetrofitUtils().getCharacterDtoList(numberOfPage).execute()

            if (callResponse.isSuccessful) {
                val apiResponseDto = callResponse.body()

                if (apiResponseDto != null) {
                    numberOfPage = numberOfPage + 1
                    val infoWrapperMapped = CharacterModelMapper.toModel(apiResponseDto)
                    characterListCache.addAll(infoWrapperMapped.characterList)
                    return infoWrapperMapped.copy(characterList = characterListCache)
                } else {
                    throw Exception("Null response from server")
                }
            } else {
                throw Exception("Error Api request: ${callResponse.code()}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception("Error to get character List", e)
        }
    }
}
