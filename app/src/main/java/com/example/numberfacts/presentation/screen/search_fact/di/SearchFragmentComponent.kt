package com.example.numberfacts.presentation.screen.search_fact.di

import com.example.numberfacts.application.fragment_factory.FragmentProvider
import dagger.Component

@Component(
    dependencies = [SearchFragmentDependencies::class],
    modules = [SearchFragmentModule::class]
)
interface SearchFragmentComponent : FragmentProvider