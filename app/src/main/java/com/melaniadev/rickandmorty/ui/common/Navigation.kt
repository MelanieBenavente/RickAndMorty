package com.melaniadev.rickandmorty.ui.common

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.melaniadev.rickandmorty.ui.features.character.CharacterDetailView
import com.melaniadev.rickandmorty.ui.features.home.HomeView

enum class NavigationRoute {
    HOME, CHARACTER_DETAIL, BLOCK_ERROR
}

fun NavGraphBuilder.getNavGraph(navigationController: NavHostController) {
    composable(NavigationRoute.HOME.name) {
        HomeView(navigationController)
    }

    composable(NavigationRoute.CHARACTER_DETAIL.name + "/{id}") {
        backStackEntry ->
        backStackEntry.arguments?.getString("id")?.toInt()?.let {
            CharacterDetailView(
                navigationController, it
            )
        }
    }

    composable(NavigationRoute.BLOCK_ERROR.name) {
        ErrorFullScreenView(
            navigationController
        )
    }
}