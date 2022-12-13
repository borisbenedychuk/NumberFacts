package com.example.numberfacts.application.di

import com.example.numberfacts.application.fragment_factory.FragmentKey
import com.example.numberfacts.application.fragment_factory.FragmentProvider
import com.example.numberfacts.presentation.screen.search_fact.SearchFactFragment
import com.example.numberfacts.presentation.screen.search_fact.di.DaggerSearchFactFragmentComponent
import com.example.numberfacts.presentation.screen.search_fact.di.SearchFactFragmentComponent
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class FragmentProviderModule {

    @Binds
    @IntoMap
    @FragmentKey(SearchFactFragment::class)
    abstract fun bindSearchFactProvider(component: SearchFactFragmentComponent): FragmentProvider

    companion object {
        @Provides
        fun provideSearchFactFragmentProvider(component: AppComponent): SearchFactFragmentComponent =
            DaggerSearchFactFragmentComponent.builder()
                .searchFactFragmentDependencies(component)
                .build()
    }
}