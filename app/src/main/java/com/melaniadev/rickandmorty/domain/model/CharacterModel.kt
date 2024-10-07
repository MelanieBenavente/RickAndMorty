package com.melaniadev.rickandmorty.domain.model

import java.io.Serializable

data class CharacterModel(
    val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val type: String? = null,
    val gender: Gender,
    val origin: String,
    val location: String,
    val image: String,
    val episodes: List<String>
) : Serializable

enum class Status(val statusName: String) {
    ALIVE("ALIVE"),
    DEAD("DEAD"),
    UNKNOWN("UNKNOWN")
}

enum class Gender(val genderName: String) {
    FEMALE("FEMALE"),
    MALE("MALE"),
    GENDERLESS("GENDERLESS"),
    UNKNOWN("UNKNOWN")
}

