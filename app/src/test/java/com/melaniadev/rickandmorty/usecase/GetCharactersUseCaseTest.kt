package com.melaniadev.rickandmorty.usecase

import com.melaniadev.rickandmorty.domain.model.CharacterInfoWrapper
import com.melaniadev.rickandmorty.domain.repository.RickAndMortyRepository
import com.melaniadev.rickandmorty.domain.usecase.GetCharactersUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCharactersUseCaseTest : CommonUseCaseTest() {

    private lateinit var getCharactersUseCase: GetCharactersUseCase
    private lateinit var rickAndMortyRepository: RickAndMortyRepository

    @Before
    fun setUp() {
        rickAndMortyRepository = mockk()
        getCharactersUseCase = GetCharactersUseCase(rickAndMortyRepository)
    }

    @Test
    fun `given getCharacterList() from repository returns characterInfoWrapper, when invoke usecase then returns expected result`() { //GIVEN
        val characterInfoWrapper: CharacterInfoWrapper = mockk()
        coEvery { rickAndMortyRepository.getCharacterList() } answers { characterInfoWrapper } //WHEN
        var result: CharacterInfoWrapper? = null
        testCoroutineDispatcher.runTest {
            getCharactersUseCase().collect { result = it }
        } //THEN
        checkNotNull(result)
        assert(result == characterInfoWrapper)
        coVerify(exactly = 1) { rickAndMortyRepository.getCharacterList() }
    }
}