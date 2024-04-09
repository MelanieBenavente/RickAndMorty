package com.melaniadev.rickandmorty.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.melaniadev.rickandmorty.databinding.SingleItemViewBinding
import com.melaniadev.rickandmorty.domain.model.CharacterModel

class CharactersAdapter(private val characterList: List<CharacterModel>): RecyclerView.Adapter<CharactersAdapter.CharacterHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): CharacterHolder {
        val binding = SingleItemViewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterHolder(binding)
    }


    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        holder.printHolder(characterList.get(position))
    }

    override fun getItemCount():
            Int = characterList.size

    class CharacterHolder(val binding: SingleItemViewBinding) : ViewHolder(binding.root){
        fun printHolder(character: CharacterModel){
            binding.primertext.text = character.name
        }
    }
}