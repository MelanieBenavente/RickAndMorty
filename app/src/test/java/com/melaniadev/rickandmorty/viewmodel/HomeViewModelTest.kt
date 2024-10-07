package com.melaniadev.rickandmorty.viewmodel

import app.cash.turbine.test
import com.melaniadev.rickandmorty.domain.model.CharacterInfoWrapper
import com.melaniadev.rickandmorty.domain.usecase.GetCharactersUseCase
import com.melaniadev.rickandmorty.ui.features.home.viewmodel.HomeViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test

class HomeViewModelTest : CommonViewModelTest() {

    val getCharactersUseCase = mockk<GetCharactersUseCase>()
    private val viewModel = HomeViewModel(getCharactersUseCase)

    @After
    fun resetDispatchers() {
        Dispatchers.resetMain()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `given getCharactersUseCase() returns flowOf()characterInfoWrapper, when calls requestCharactersFromModel() then State is Loading and CharacterInfoRecived`() {
        //GIVEN
        val characterInfoWrapper = mockk<CharacterInfoWrapper>()
        every { characterInfoWrapper.characterList } returns listOf(mockk())
        TestScope(StandardTestDispatcher()).runTest {
            every { getCharactersUseCase() } returns flowOf(characterInfoWrapper)
            initDispatchers(this.coroutineContext)
            viewModel.homeUiModelState.test {
                //WHEN
                viewModel.requestCharacters()
                //THEN
                val state0 = awaitItem()
                assert(!state0.isLoading)
                assert(!state0.isError)

                val state1 = awaitItem()
                assert(state1.isLoading)
                assert(!state1.isError)

                val state2 = awaitItem()
                assert(!state2.isLoading)
                assert(!state2.isError)
                assert(state2.characterInfoWrapper.characterList.isNotEmpty())
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `given_getCharactersUseCase() returns flowOf()characterInfoWrapper, when calls requestCharactersFromModel() then State is Loading and CharacterInfoRecived`() {
        //GIVEN
        TestScope(StandardTestDispatcher()).runTest {
            every { getCharactersUseCase() } throws Exception("simulated error")
            initDispatchers(this.coroutineContext)
            viewModel.homeUiModelState.test {
                //WHEN
                viewModel.requestCharacters()
                //THEN
                val state0 = awaitItem()
                assert(!state0.isLoading)
                assert(!state0.isError)
                val state1 = awaitItem()
                assert(state1.isLoading)
                assert(!state1.isError)
                val state2 = awaitItem()
                assert(!state2.isLoading)
                assert(state2.isError)
            }
        }
    }
}