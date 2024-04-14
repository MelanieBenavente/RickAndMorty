package com.melaniadev.rickandmorty.viewmodel

import app.cash.turbine.test
import com.melaniadev.rickandmorty.domain.model.CharacterInfoWrapper
import com.melaniadev.rickandmorty.domain.usecase.GetCharactersUseCase
import com.melaniadev.rickandmorty.ui.HomeViewModel
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
import kotlin.test.assertTrue

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
        TestScope(StandardTestDispatcher()).runTest {
            every { getCharactersUseCase() } returns flowOf(characterInfoWrapper)
            //WHEN
            initDispatchers(this.coroutineContext)
            viewModel.requestCharactersFromModel()
            //THEN
            viewModel.state.test {
                assertTrue(awaitItem() is HomeViewModel.State.Loading)
                assertTrue(awaitItem() is HomeViewModel.State.CharacterInfoRecived)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `given_getCharactersUseCase() returns flowOf()characterInfoWrapper, when calls requestCharactersFromModel() then State is Loading and CharacterInfoRecived`() {
        //GIVEN
        TestScope(StandardTestDispatcher()).runTest {
            every { getCharactersUseCase() } throws Exception("simulated error")
            //WHEN
            initDispatchers(this.coroutineContext)
            viewModel.requestCharactersFromModel()
            //THEN
            viewModel.state.test {
                assertTrue(awaitItem() is HomeViewModel.State.Loading)
                assertTrue(awaitItem() is HomeViewModel.State.ScreenError)
            }
        }
    }
}