package com.melaniadev.rickandmorty.data.common

import com.melaniadev.rickandmorty.data.dto.ApiResponseDto
import com.melaniadev.rickandmorty.data.dto.CharacterDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    companion object {

        const val URL_BASE: String = "https://rickandmortyapi.com/api/"
        const val KEY_PAGE_PARAM: String = "page"
    }
    @GET("character")
    fun getCharacterDtoList(@Query(KEY_PAGE_PARAM)page: Int) : Call<ApiResponseDto>
}