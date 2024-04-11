package com.melaniadev.rickandmorty.data

import com.melaniadev.rickandmorty.data.common.RetrofitUtils
import com.melaniadev.rickandmorty.domain.model.CharacterModel
import com.melaniadev.rickandmorty.domain.model.mapper.CharacterModelMapper
import com.melaniadev.rickandmorty.domain.repository.RickAndMortyRepository

class RickAndMortyRepositoryImpl : RickAndMortyRepository {
    var numberOfPage: Int = 1
    override suspend fun getCharacterList(): List<CharacterModel> {

        try {
            val callResponse = RetrofitUtils.getRetrofitUtils().getCharacterDtoList(numberOfPage).execute()

            if (callResponse.isSuccessful) {
                val apiResponseDto = callResponse.body()

                if (apiResponseDto != null) {
                    numberOfPage = numberOfPage + 1
                    return CharacterModelMapper.toModel(apiResponseDto)
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
