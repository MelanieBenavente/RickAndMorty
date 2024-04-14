package com.melaniadev.rickandmorty.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ApiResponseDto(
    @SerializedName("info") val info: InfoDto,
    @SerializedName("results") val characterList: List<CharacterDto>) : Serializable

data class InfoDto(
    @SerializedName("pages") val pages: Int?, @SerializedName("next") val nextPage: String?) :
    Serializable {}

data class CharacterDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String?,
    @SerializedName("species") val species: String,
    @SerializedName("type") val type: String? = null,
    @SerializedName("gender") val gender: String?,
    @SerializedName("origin") val origin: OriginDto,
    @SerializedName("location") val location: LocationDto,
    @SerializedName("image") val image: String,
    @SerializedName("episode") val episodes: List<String>,
    @SerializedName("url") val url: String? = null,
    @SerializedName("created") val created: String? = null) : Serializable

data class OriginDto(
    @SerializedName("name") val originName: String,
    @SerializedName("url") val originLocationUrl: String?) : Serializable

data class LocationDto(
    @SerializedName("name") val locationName: String,
    @SerializedName("url") val locationUrl: String?) : Serializable