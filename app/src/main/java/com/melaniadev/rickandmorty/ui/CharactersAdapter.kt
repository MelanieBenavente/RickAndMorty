package com.melaniadev.rickandmorty.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.melaniadev.rickandmorty.R
import com.melaniadev.rickandmorty.databinding.SingleItemViewBinding
import com.melaniadev.rickandmorty.domain.model.CharacterInfoWrapper
import com.melaniadev.rickandmorty.domain.model.CharacterModel

class CharactersAdapter(private val characterInfoWrapper: CharacterInfoWrapper): RecyclerView.Adapter<CharactersAdapter.CharacterHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): CharacterHolder {
        val binding = SingleItemViewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterHolder(binding)
    }


    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        holder.printHolder(characterInfoWrapper.characterList.get(position))
    }

    override fun getItemCount():
            Int = characterInfoWrapper.characterList.size

    class CharacterHolder(val binding: SingleItemViewBinding) : ViewHolder(binding.root){

        fun printHolder(character: CharacterModel){
            binding.primertext.text = character.name
            binding.segundotext.text = character.gender.toString()
            binding.tercertext.text = character.status.toString()

            val requestOptions = RequestOptions()
                .transform(RoundedCorners(50))
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(R.drawable.placeholder_rickandmorty)
            Glide.with(binding.root)
                .load(character.image)
                .apply(requestOptions)
                .into(binding.characterImageview)
        }
    }
}