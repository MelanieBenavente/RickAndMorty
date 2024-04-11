package com.melaniadev.rickandmorty.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melaniadev.rickandmorty.domain.model.CharacterInfoWrapper

import com.melaniadev.rickandmorty.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _state = MutableSharedFlow<State>()
    val state = _state.asSharedFlow()

    fun requestCharactersFromModel() {
        viewModelScope.launch {
            getCharactersUseCase()
                .onStart { _state.emit(State.Loading) }
                .catch { _state.emit(State.ScreenError) }
                .collect { _state.emit(State.CharacterInfoRecived(it)) }
        }
    }

    sealed class State {
        object Loading : State()
        object ScreenError : State()
        data class CharacterInfoRecived(
            val characterInfoWrapper: CharacterInfoWrapper) : State()
    }
}