package com.example.numberfacts.presentation.screen.search_fact.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.numberfacts.application.view_model_factory.ViewModelKey
import com.example.numberfacts.presentation.screen.search_fact.SearchFactFragment
import com.example.numberfacts.presentation.screen.search_fact.SearchFactViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchFragmentModule {

    @Binds
    abstract fun bindFragment(fragment: SearchFactFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(SearchFactViewModel::class)
    abstract fun bindViewModel(viewModel: SearchFactViewModel): ViewModel
}