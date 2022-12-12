package com.example.numberfacts.presentation.screen.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.numberfacts.R
import com.example.numberfacts.databinding.FragmentDetailsBinding
import com.example.numberfacts.presentation.screen.base.BaseFragment

class DetailsFragment : BaseFragment(R.layout.fragment_details) {

    private val args by navArgs<DetailsFragmentArgs>()

    private val binding by viewBinding(FragmentDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textFact.text = args.item.fact
        binding.textNumber.text = args.item.number.toString()
    }
}