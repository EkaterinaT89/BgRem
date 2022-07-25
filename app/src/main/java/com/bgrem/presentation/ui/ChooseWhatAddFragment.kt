package com.bgrem.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bgrem.R
import com.bgrem.databinding.FragmentChooseWhatAddBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseWhatAddFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentChooseWhatAddBinding.inflate(
            inflater,
            container,
            false
        )

        with(binding) {

        }

        return binding.root

    }

}