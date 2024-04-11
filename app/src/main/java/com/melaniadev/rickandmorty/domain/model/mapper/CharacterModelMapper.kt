package com.melaniadev.rickandmorty.domain.model.mapper

import com.melaniadev.rickandmorty.data.dto.ApiResponseDto
import com.melaniadev.rickandmorty.data.dto.CharacterDto
import com.melaniadev.rickandmorty.domain.model.CharacterModel
import com.melaniadev.rickandmorty.domain.model.Gender
import com.melaniadev.rickandmorty.domain.model.Status

class CharacterModelMapper {

    companion object {
        fun toModel(apiResponseDto: ApiResponseDto): List<CharacterModel> {
            val characterModelList: MutableList<CharacterModel> = mutableListOf()
            for (characterDto: CharacterDto in apiResponseDto.characterList) {
                val statusEnum = getStatusEnum(characterDto.status)
                val genderEnum = getGenderEnum(characterDto.gender)
                val characterModel = CharacterModel(
                    id = characterDto.id,
                    name = characterDto.name,
                    status = statusEnum,
                    species = characterDto.species,
                    type = characterDto.type,
                    gender = genderEnum,
                    origin = characterDto.origin.originName,
                    location = characterDto.location.locationName,
                    image = characterDto.image,
                    episodes = listOf()
                )
                characterModelList.add(characterModel)
            }
            return characterModelList
        }

        private fun getStatusEnum(status: String?): Status {
            return when (status) {
                "Alive" -> Status.ALIVE
                "Dead" -> Status.DEAD
                else -> Status.UNKNOWN
            }
        }

        private fun getGenderEnum(gender: String?): Gender {
            return when (gender) {
                "Female" -> Gender.FEMALE
                "Male" -> Gender.MALE
                "Genderless" -> Gender.GENDERLESS
                else -> Gender.UNKNOWN
            }
        }
    }
}
