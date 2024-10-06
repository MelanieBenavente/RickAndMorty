package com.melaniadev.rickandmorty.ui.features.character.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melaniadev.rickandmorty.domain.model.CharacterModel
import com.melaniadev.rickandmorty.domain.usecase.GetCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : ViewModel() {
    private val _characterModelState = MutableStateFlow<CharacterModel?>(null)
    val characterModelState = _characterModelState.asStateFlow()

    fun getCharacterDetail(id: Int) {
        viewModelScope.launch {
            getCharacterByIdUseCase(id)
                .collect { response ->
                    _characterModelState.value = response
                }
        }
    }
}


