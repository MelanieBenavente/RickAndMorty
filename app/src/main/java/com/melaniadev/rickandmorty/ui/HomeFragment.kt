package com.melaniadev.rickandmorty.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.melaniadev.rickandmorty.databinding.HomeFragmentBinding
import com.melaniadev.rickandmorty.domain.model.CharacterModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private var characterList = listOf<CharacterModel>()
   // private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(layoutInflater)
        val myView = binding.root
        super.onCreateView(inflater, container, savedInstanceState)
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachObservers()
        homeViewModel.requestCharactersFromModel()
    }

    private fun attachObservers() {
        homeViewModel.state.onEach(::handleState).launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleState(state: HomeViewModel.State) {
        when (state) {
            is HomeViewModel.State.CharacterListRecived -> {
                    printCharacterList(state.characterList)
            }
        }
    }

    private fun printCharacterList(listRecived : List<CharacterModel>) {
        characterList = listRecived
        //charactersAdapter.setCharacterList(characterList)
    }
}