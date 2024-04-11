package com.melaniadev.rickandmorty.domain.model

import java.io.Serializable

data class CharacterInfoWrapper(val characterList: List<CharacterModel>, val haveMorePages: Boolean)