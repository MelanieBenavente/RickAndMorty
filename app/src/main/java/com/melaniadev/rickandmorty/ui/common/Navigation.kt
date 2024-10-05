package com.melaniadev.rickandmorty.ui.common

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.melaniadev.rickandmorty.ui.features.home.CharacterListView

enum class Routes {
    HOME, CHARACTER_DETAIL, BLOCK_ERROR
}

fun NavGraphBuilder.getNavGraph(navigationController: NavHostController) {
    composable(Routes.HOME.name) {
        CharacterListView(navigationController)
    }

    /*composable(
        Routes.CHARACTER_DETAIL.name + "/{name}",
        enterTransition = {
            fadeIn(
                animationSpec = tween(
                    100, easing = LinearEasing
                )
            ) + slideIntoContainer(
                animationSpec = tween(100, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Start
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            ) + slideOutOfContainer(
                animationSpec = tween(300, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.End
            )
        }
    ) {
        backStackEntry -> CharacterDetailScreen(
            navigationController, backStackEntry.arguments?.getString("name").orEmpty()
        )
    }*/
}