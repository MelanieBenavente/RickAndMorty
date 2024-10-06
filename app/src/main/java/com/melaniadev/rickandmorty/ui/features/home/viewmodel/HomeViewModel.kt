package com.melaniadev.rickandmorty.ui.features.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.melaniadev.rickandmorty.domain.usecase.GetCharactersUseCase
import com.melaniadev.rickandmorty.ui.features.home.uimodel.HomeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _homeUiModelState = MutableStateFlow(HomeUiModel())
    val homeUiModelState = _homeUiModelState.asStateFlow()

    fun requestCharacters() {
        viewModelScope.launch {
            getCharactersUseCase()
                .onStart { _homeUiModelState.emit(_homeUiModelState.value.copy(isLoading = true, isError = false)) }
                .catch { _homeUiModelState.emit(HomeUiModel(isError = true)) }
                .collect { response -> _homeUiModelState.emit(HomeUiModel(characterInfoWrapper = response)) }
        }
    }
}