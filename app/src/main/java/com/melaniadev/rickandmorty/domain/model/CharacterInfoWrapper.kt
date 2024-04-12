package com.melaniadev.rickandmorty.domain.model

data class CharacterInfoWrapper(val characterList: List<CharacterModel>, val haveMorePages: Boolean)