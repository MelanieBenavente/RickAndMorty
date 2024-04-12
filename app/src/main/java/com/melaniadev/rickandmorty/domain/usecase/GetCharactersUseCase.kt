package com.melaniadev.rickandmorty.domain.usecase

import com.melaniadev.rickandmorty.domain.common.usecase.UseCase
import com.melaniadev.rickandmorty.domain.model.CharacterInfoWrapper
import com.melaniadev.rickandmorty.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(val rickAndMortyRepository: RickAndMortyRepository) : UseCase<Unit, CharacterInfoWrapper>() {
    override fun run(params: Unit): Flow<CharacterInfoWrapper> = flow {
        val characters = rickAndMortyRepository.getCharacterList()
        emit(characters)
    }

}