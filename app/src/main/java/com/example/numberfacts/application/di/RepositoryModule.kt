package com.example.numberfacts.application.di


import com.example.numberfacts.data.repository.di.DaggerNumberRepositoryComponent
import com.example.numberfacts.domain.repository.NumberFactRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideNumberFactRepository(component: AppComponent): NumberFactRepository =
        DaggerNumberRepositoryComponent.builder()
            .numberRepositoryDependencies(component)
            .build()
            .numberFactRepository
}