package com.example.numberfacts.application.di


import com.example.numberfacts.data.repository.di.DaggerNumberRepositoryComponent
import com.example.numberfacts.domain.repository.NumberRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideNumberRepository(component: AppComponent): NumberRepository =
        DaggerNumberRepositoryComponent.builder()
            .numberRepositoryDependencies(component)
            .build()
            .numberRepository
}