package com.melaniadev.rickandmorty.ui.common.extensions

import com.melaniadev.rickandmorty.R
import com.melaniadev.rickandmorty.domain.model.Gender
import com.melaniadev.rickandmorty.domain.model.Status

fun Gender.getGenderIconResource(): Int {
    return when (this) {
        Gender.FEMALE -> R.drawable.gender_female_svg
        Gender.MALE -> R.drawable.gender_male_svg
        Gender.GENDERLESS -> R.drawable.gender_genderless_svg
        Gender.UNKNOWN -> R.drawable.gender_unknown_svg
    }
}

fun Status.getStatusIconResource(): Int {
    return when (this) {
        Status.ALIVE -> R.drawable.status_alive
        Status.DEAD -> R.drawable.status_dead
        Status.UNKNOWN -> R.drawable.gender_unknown_svg
    }
}