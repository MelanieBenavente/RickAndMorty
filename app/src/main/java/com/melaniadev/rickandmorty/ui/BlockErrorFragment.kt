package com.melaniadev.rickandmorty.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.melaniadev.rickandmorty.databinding.BlockErrorFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlockErrorFragment : Fragment() {

    private lateinit var binding: BlockErrorFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = BlockErrorFragmentBinding.inflate(layoutInflater)
        val mView = binding.root
        super.onCreateView(inflater, container, savedInstanceState)
        binding.backErrorScreenBtn.setOnClickListener { view1 -> requireActivity().onBackPressed() }
        return mView
    }
}