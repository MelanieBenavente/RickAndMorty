package com.melaniadev.rickandmorty.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.melaniadev.rickandmorty.R
import com.melaniadev.rickandmorty.databinding.CharacterDetailFragmentBinding
import com.melaniadev.rickandmorty.domain.model.CharacterModel
import com.melaniadev.rickandmorty.domain.model.Gender
import com.melaniadev.rickandmorty.domain.model.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {
    companion object {

        val KEY_CHARACTER = "characterInfo"
        fun newInstance(character: CharacterModel): CharacterDetailFragment {
            val characterDetailFragment = CharacterDetailFragment()
            val bundle = Bundle()
            bundle.putSerializable(KEY_CHARACTER, character)
            characterDetailFragment.arguments = bundle
            return characterDetailFragment
        }
    }

    private lateinit var binding: CharacterDetailFragmentBinding
    private val character: CharacterModel by lazy { requireArguments().getSerializable(KEY_CHARACTER) as CharacterModel }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = CharacterDetailFragmentBinding.inflate(layoutInflater)
        val mView = binding.root
        super.onCreateView(inflater, container, savedInstanceState)
        bindViews()
        return mView
    }
    private fun bindViews(){
        binding.apply {
            characterName.text = character.name
            characterStatus.text = character.status.toString()
            characterSpecie.text = character.species
            characterGender.text = character.gender.toString()
            characterOrigin.text = character.origin
            characterLocation.text = character.location
            setImageByGender()
            setColorByStatus()
        }
        val requestOptions = RequestOptions().transform(RoundedCorners(30))
        Glide.with(binding.root).load(character.image).apply(requestOptions)
            .into(binding.characterImage)
    }
    private fun setImageByGender(){
        val genderType = when (character.gender) {
            Gender.FEMALE -> R.drawable.gender_female_svg
            Gender.MALE -> R.drawable.gender_male_svg
            Gender.GENDERLESS -> R.drawable.gender_genderless_svg
            Gender.UNKNOWN -> R.drawable.gender_unknown_svg
        }
        binding.genderView.setImageResource(genderType)
    }
    private fun setColorByStatus(){
        val statusColor = when (character.status) {
            Status.ALIVE -> R.color.green_alive
            Status.DEAD -> R.color.red_dead
            Status.UNKNOWN -> R.color.black
        }
        val statusDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.shape_status_background)
        statusDrawable?.let {
            val wrappedDrawable = DrawableCompat.wrap(it)
            DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(requireContext(), statusColor))
            binding.characterStatus.background = wrappedDrawable
        }
    }
}