package com.example.numberfacts.data.repository.di

import com.example.numberfacts.data.cache.AppDB
import retrofit2.Retrofit

interface NumberRepositoryDependencies {
    val db: AppDB
    val retrofit: Retrofit
}