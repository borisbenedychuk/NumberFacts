package com.example.numberfacts.application.di

import android.content.Context
import com.example.numberfacts.application.fragment_factory.FragmentFactory
import com.example.numberfacts.data.repository.di.NumberFactRepositoryDependencies
import com.example.numberfacts.presentation.screen.search_fact.di.SearchFactFragmentDependencies
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CommonModule::class, RepositoryModule::class, FragmentProviderModule::class])
interface AppComponent : NumberFactRepositoryDependencies, SearchFactFragmentDependencies {

    val fragmentFactory: FragmentFactory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}