package com.example.numberfacts.application.di

import android.content.Context
import com.example.numberfacts.application.fragment_factory.FragmentFactory
import com.example.numberfacts.data.repository.di.NumberRepositoryDependencies
import com.example.numberfacts.presentation.screen.search_fact.di.SearchFragmentDependencies
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CommonModule::class, RepositoryModule::class, FragmentProviderModule::class])
interface AppComponent : NumberRepositoryDependencies, SearchFragmentDependencies {

    val fragmentFactory: FragmentFactory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
        ): AppComponent
    }
}