package com.example.numberfacts.data.repository.di

import com.example.numberfacts.domain.repository.NumberFactRepository
import dagger.Component

@Component(
    dependencies = [NumberFactRepositoryDependencies::class],
    modules = [NumberRepositoryModule::class]
)
interface NumberRepositoryComponent {
    val numberFactRepository: NumberFactRepository
}