package com.melaniadev.rickandmorty.data.datasource.remote

import com.melaniadev.rickandmorty.data.datasource.remote.common.RetrofitUtils
import com.melaniadev.rickandmorty.data.datasource.remote.dto.ApiResponseDto

class RemoteDataSource {
    var numberOfPage: Int = 1

    fun getCharacterListFromRemote(): ApiResponseDto {
        try {
            val callResponse =
                RetrofitUtils.getRetrofitUtils().getCharacterDtoList(numberOfPage).execute()
            if (callResponse.isSuccessful) {
                val apiResponseDto = callResponse.body()
                if (apiResponseDto != null) {
                    numberOfPage = numberOfPage + 1
                    return apiResponseDto
                } else {
                    throw Exception("Null response from server")
                }
            } else {
                throw Exception("Error Api request: ${callResponse.code()}")
            }
        } catch (e: Exception) {
            throw e
        }
    }
}