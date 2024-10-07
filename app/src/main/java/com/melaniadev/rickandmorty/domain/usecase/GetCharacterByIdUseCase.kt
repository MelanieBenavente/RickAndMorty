package com.melaniadev.rickandmorty.domain.usecase

import com.melaniadev.rickandmorty.domain.common.usecase.UseCase
import com.melaniadev.rickandmorty.domain.model.CharacterModel
import com.melaniadev.rickandmorty.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(private val rickAndMortyRepository: RickAndMortyRepository) : UseCase<Int, CharacterModel>() {
    override fun run(params: Int): Flow<CharacterModel> = flow {
        val characterDetail = rickAndMortyRepository.getCharacterInfo(params)
        emit(characterDetail)
    }

}