package com.melaniadev.rickandmorty.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.melaniadev.rickandmorty.R
import com.melaniadev.rickandmorty.databinding.SingleButtonViewBinding
import com.melaniadev.rickandmorty.databinding.SingleItemViewBinding
import com.melaniadev.rickandmorty.domain.model.CharacterInfoWrapper
import com.melaniadev.rickandmorty.domain.model.CharacterModel
import com.melaniadev.rickandmorty.domain.model.Gender
import com.melaniadev.rickandmorty.domain.model.Status

class CharactersAdapter(private val requestMoreCharacters: () -> Unit, private val navigateToDetail: (CharacterModel) -> Unit) :
    RecyclerView.Adapter<CharactersAdapter.CharacterHolder>() {

    val SINGLE_TYPE = 1
    val SINGLE_LAST_TYPE = 2

    var characterInfoWrapper: CharacterInfoWrapper? = null
    var isLoading: Boolean = false

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): CharacterHolder {
        val binding = if (viewType == SINGLE_TYPE) {
            SingleItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        } else {
            SingleButtonViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }
        return CharacterHolder(binding)
    }


    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        if (getItemViewType(position) == SINGLE_TYPE) {
            characterInfoWrapper!!.characterList.let {
                holder.printHolder(it.get(position), {character: CharacterModel -> navigateToDetail(character)})
            }
        } else {
            characterInfoWrapper?.characterList.let {
                if (characterInfoWrapper?.haveMorePages == true && !isLoading) {
                    holder.printButton(requestMoreCharacters)
                }
            }
            if (isLoading) {
                holder.showLoading()
            }
        }
    }

    override fun getItemCount(): Int {
        if (characterInfoWrapper?.characterList != null) {
            return characterInfoWrapper!!.characterList.size + 1
        }
        return 1
    }

    override fun getItemViewType(position: Int): Int {
        characterInfoWrapper?.characterList?.let {
            if (position < it.size) {
                return SINGLE_TYPE
            }
        }
        return SINGLE_LAST_TYPE
    }

    class CharacterHolder(val binding: ViewBinding) : ViewHolder(binding.root) {

        fun showLoading() {
            binding as SingleButtonViewBinding
            //todo!!! binding apply in all bindings
            binding.lottieLoading.visibility = View.VISIBLE
            binding.showMoreBtn.visibility = View.INVISIBLE
        }

        fun printButton(requestMoreCharacters: () -> Unit) {
            binding as SingleButtonViewBinding
            binding.showMoreBtn.visibility = View.VISIBLE
            binding.lottieLoading.visibility = View.INVISIBLE
            binding.showMoreBtn.setOnClickListener {
                requestMoreCharacters()
            }
        }

        fun printHolder(character: CharacterModel, navigateToDetail: (CharacterModel) -> Unit) {
            binding as SingleItemViewBinding
            binding.singleItemViewContainer.setOnClickListener {
                navigateToDetail(character)
            }
            binding.characterNameTxt.text = character.name
            binding.statusTxt.text = character.status.toString()
            binding.genderTxt.text = character.gender.toString()

            val genderType = when (character.gender) {
                Gender.FEMALE -> R.drawable.gender_female_svg
                Gender.MALE -> R.drawable.gender_male_svg
                Gender.GENDERLESS -> R.drawable.gender_genderless_svg
                Gender.UNKNOWN -> R.drawable.gender_unknown_svg
            }
            binding.genderView.setImageResource(genderType)

            val statusType = when (character.status){
                Status.ALIVE -> R.drawable.status_alive
                Status.DEAD -> R.drawable.status_dead
                Status.UNKNOWN -> R.drawable.gender_unknown_svg
            }
            binding.statusView.setImageResource(statusType)


            val requestOptions = RequestOptions().transform(RoundedCorners(30))
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(R.drawable.placeholder_rickandmorty)
            Glide.with(binding.root).load(character.image).apply(requestOptions)
                .into(binding.characterImageview)
        }
    }
}