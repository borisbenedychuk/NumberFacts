package com.example.numberfacts.presentation.screen.search_fact.di

import com.example.numberfacts.application.fragment_factory.FragmentProvider
import dagger.Component

@Component(
    dependencies = [SearchFactFragmentDependencies::class],
    modules = [SearchFactFragmentModule::class]
)
interface SearchFactFragmentComponent : FragmentProvider