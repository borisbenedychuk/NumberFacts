package com.example.numberfacts.data.repository.di

import com.example.numberfacts.domain.repository.NumberRepository
import dagger.Component

@Component(
    dependencies = [NumberRepositoryDependencies::class],
    modules = [NumberRepositoryModule::class]
)
interface NumberRepositoryComponent {
    val numberRepository: NumberRepository
}