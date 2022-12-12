package com.example.numberfacts.presentation.screen.search_fact

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.numberfacts.R
import com.example.numberfacts.application.view_model_factory.ViewModelFactory
import com.example.numberfacts.databinding.FragmentSearchFactBinding
import com.example.numberfacts.presentation.model.details.asItem
import com.example.numberfacts.presentation.model.search_fact.SearchFactEvent
import com.example.numberfacts.presentation.model.search_fact.SearchFactIntent
import com.example.numberfacts.presentation.screen.base.BaseFragment
import com.example.numberfacts.presentation.utils.adapters
import com.example.numberfacts.presentation.utils.filterIsInstance
import javax.inject.Inject

class SearchFactFragment @Inject constructor(
    private val viewModelFactory: ViewModelFactory,
) : BaseFragment(R.layout.fragment_search_fact) {

    private val viewModel by viewModels<SearchFactViewModel> { viewModelFactory }

    private val binding by viewBinding(FragmentSearchFactBinding::bind)

    private val adapter by adapters(
        recyclerView = { binding.recyclerView },
        initializer = {
            SearchFactAdapter {
                viewModel.pushAction(SearchFactIntent.PickHistory(it))
            }
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindNumberText()
        bindNavigation()
        bindRecyclerView()
        bindProgressbar()
        bindButtons()
        bindError()
    }

    private fun bindButtons() = with(binding) {
        buttonNumber.setOnClickListener {
            textField.text?.toString()?.toIntOrNull()?.let {
                viewModel.pushAction(SearchFactIntent.SearchNumber)
            }
        }
        buttonRandomNumber.setOnClickListener {
            viewModel.pushAction(SearchFactIntent.SearchRandomNumber)
        }
    }

    private fun bindNumberText() = with(binding.textField) {
        doOnTextChanged { text, _, _, _ ->
            text?.let {
                viewModel.pushAction(
                    SearchFactIntent.ChangeText(it.toString())
                )
            }
        }
    }

    private fun bindNavigation() {
        viewModel.events
            .filterIsInstance<SearchFactEvent.DetailsScreen>()
            .subscribeWithLifecycle {
                findNavController().navigate(
                    SearchFactFragmentDirections.actionSearchFactToDetails(
                        it.model.asItem()
                    )
                )
            }
    }

    private fun bindRecyclerView() {
        binding
        viewModel.state
            .map { it.history }
            .distinctUntilChanged()
            .subscribeWithLifecycle(adapter::update)
    }

    private fun bindProgressbar() = with(binding) {
        viewModel.state
            .map { it.loading }
            .distinctUntilChanged()
            .subscribeWithLifecycle { isLoading ->
                if (loadingContainer.isVisible != isLoading) {
                    loadingContainer.isVisible = isLoading
                    textField.isEnabled = !isLoading
                    buttonNumber.isEnabled = !isLoading
                    buttonRandomNumber.isEnabled = !isLoading
                }
            }
    }

    private fun bindError() {
        viewModel.events
            .filterIsInstance<SearchFactEvent.Error>()
            .subscribeWithLifecycle {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
            }
    }
}