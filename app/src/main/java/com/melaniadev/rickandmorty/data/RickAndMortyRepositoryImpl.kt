package com.melaniadev.rickandmorty.data

import com.melaniadev.rickandmorty.domain.model.CharacterModel
import com.melaniadev.rickandmorty.domain.model.Gender
import com.melaniadev.rickandmorty.domain.model.Status
import com.melaniadev.rickandmorty.domain.repository.RickAndMortyRepository

class RickAndMortyRepositoryImpl : RickAndMortyRepository {
    override fun getCharacterList(): List<CharacterModel> {
        return mockkData()
    }

    fun mockkData(): List<CharacterModel> {
        val episodes: List<String> = listOf("episodiouno", "episodiodos", "episodiotres")
        val charactersMockkedList = listOf(
            CharacterModel(
                1,
                "Morty",
                Status.ALIVE,
                "Humano",
                "",
                Gender.MALE,
                "Tierra",
                "Rubi",
                "img",
                episodes
            ), CharacterModel(
                2,
                "Meeseeks",
                Status.DEAD,
                "Marciano",
                "",
                Gender.FEMALE,
                "Mart",
                "ozzy",
                "img",
                episodes
            ), CharacterModel(
                3,
                "Rick",
                Status.UNKNOWN,
                "FlatEarth",
                "",
                Gender.UNKNOWN,
                "PlaneEarth",
                "gusano",
                "img",
                episodes
            ), CharacterModel(
                4,
                "PickleRick",
                Status.UNKNOWN,
                "Cucumber",
                "",
                Gender.GENDERLESS,
                "Cucumberland",
                "Pickle Jar",
                "img",
                episodes
            )
        )
        return charactersMockkedList
    }
}