package com.melaniadev.rickandmorty.data.dto
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ApiResponseDto(
    @SerializedName("info") val info: InfoDto,
    @SerializedName("results") val characterList: List<CharacterDto>
) : Serializable

data class InfoDto(
    @SerializedName("pages") var pages: Int?,
    @SerializedName("next") var nextPage: String?) : Serializable

data class CharacterDto(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("status") var status: String?,
    @SerializedName("species") var species: String,
    @SerializedName("type") var type: String? = null,
    @SerializedName("gender") var gender: String?,
    @SerializedName("origin") var origin: OriginDto,
    @SerializedName("location") var location: LocationDto,
    @SerializedName("image") var image: String,
    @SerializedName("episode") var episodes: List<String>,
    @SerializedName("url") var url: String? = null,
    @SerializedName("created") var created: String? = null) : Serializable

data class OriginDto(
    @SerializedName("name") var originName: String,
    @SerializedName("url") var originLocationUrl: String?
) : Serializable

data class LocationDto(
    @SerializedName("name") var locationName: String,
    @SerializedName("url") var locationUrl: String?
) : Serializable