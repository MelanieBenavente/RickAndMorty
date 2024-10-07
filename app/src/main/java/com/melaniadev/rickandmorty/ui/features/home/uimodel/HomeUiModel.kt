package com.melaniadev.rickandmorty.ui.features.home.uimodel

import com.melaniadev.rickandmorty.domain.model.CharacterInfoWrapper

data class HomeUiModel(
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val characterInfoWrapper : CharacterInfoWrapper = CharacterInfoWrapper(emptyList(), true)
)