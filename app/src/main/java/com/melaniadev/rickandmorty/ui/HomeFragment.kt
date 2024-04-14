package com.melaniadev.rickandmorty.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.melaniadev.rickandmorty.R
import com.melaniadev.rickandmorty.databinding.HomeFragmentBinding
import com.melaniadev.rickandmorty.domain.model.CharacterModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private var charactersAdapter: CharactersAdapter? = null

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
        if(charactersAdapter == null){
            instantiateCharactersAdapter()
        }
        attachObservers()
        homeViewModel.requestCharactersFromModel()
    }

    private fun attachObservers() {
        homeViewModel.state.onEach(::handleState).launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleState(state: HomeViewModel.State) {
        when (state) {
            is HomeViewModel.State.CharacterInfoRecived -> {
                charactersAdapter?.characterInfoWrapper = state.characterInfoWrapper
                charactersAdapter?.isLoading = false
                charactersAdapter?.notifyDataSetChanged()
            }
            HomeViewModel.State.ScreenError -> {
                charactersAdapter?.isLoading = false
                showBlockError()

            }
            HomeViewModel.State.Loading -> {
                charactersAdapter?.isLoading = true
                charactersAdapter?.notifyDataSetChanged()
            }
        }
    }

    private fun showBlockError(){
        val blockErrorFragment = BlockErrorFragment()
        parentFragmentManager
            .beginTransaction()
            .add(R.id.mainActivityContainer, blockErrorFragment)
            .addToBackStack(blockErrorFragment.tag)
            .commit()
    }

    private fun navigateToDetail(characterModel: CharacterModel){
        val characterDetailFragment = CharacterDetailFragment.newInstance(characterModel)
        parentFragmentManager
            .beginTransaction()
            .add(R.id.mainActivityContainer, characterDetailFragment)
            .addToBackStack(characterDetailFragment.tag)
            .commit()
    }

    private fun instantiateCharactersAdapter(){
        charactersAdapter = CharactersAdapter ({ homeViewModel.requestCharactersFromModel() }, { character: CharacterModel -> navigateToDetail(character) })
        binding.recyclerCharacters.setHasFixedSize(true)
        binding.recyclerCharacters.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerCharacters.adapter = charactersAdapter
    }
}