package com.melaniadev.rickandmorty.ui.features.home.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.melaniadev.rickandmorty.ui.common.NavigationRoute
import com.melaniadev.rickandmorty.ui.common.components.LoadingAnimation
import com.melaniadev.rickandmorty.ui.common.extensions.isScrolledToTheEnd
import com.melaniadev.rickandmorty.ui.features.home.uimodel.HomeUiModel
import com.melaniadev.rickandmorty.ui.features.home.viewmodel.HomeViewModel

@Composable
fun HomeView(
    navigationController: NavHostController
) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val state = homeViewModel.homeUiModelState.collectAsState()
    homeViewModel.requestCharacters()
    CharacterListView(
        state = state,
        navigationController = navigationController,
        requestCharacters = { homeViewModel.requestCharacters() })
}

@Composable
private fun CharacterListView(
    state: State<HomeUiModel>,
    navigationController: NavHostController,
    requestCharacters: () -> Unit
) {
    val listState = rememberLazyListState()
    val derivedScrollToBottomState by remember { derivedStateOf { listState.isScrolledToTheEnd() } }

    if (!state.value.isLoading && derivedScrollToBottomState && state.value.characterInfoWrapper.haveMorePages
    ) {
        requestCharacters()
    }
    if (state.value.isError) {
        navigationController.navigate(NavigationRoute.BLOCK_ERROR.name)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp),
        state = listState
    ) {
        item { TopBarComponent() }
        items(items = state.value.characterInfoWrapper.characterList, key = { it.id })
        { character ->
            CharacterItemComponent(character, navigationController)
        }

        item { if (state.value.isLoading) LoadingAnimation() }
    }
}