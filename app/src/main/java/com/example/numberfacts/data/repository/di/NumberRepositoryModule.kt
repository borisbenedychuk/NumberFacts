package com.example.numberfacts.data.repository.di

import com.example.numberfacts.data.cache.AppDB
import com.example.numberfacts.data.datasource.NumberFactCacheDatasource
import com.example.numberfacts.data.datasource.NumberFactCacheDatasourceImpl
import com.example.numberfacts.data.datasource.NumberFactRemoteDatasource
import com.example.numberfacts.data.datasource.NumberFactRemoteDatasourceImpl
import com.example.numberfacts.data.remote.api.NumberFactApi
import com.example.numberfacts.data.repository.NumberFactRepositoryImpl
import com.example.numberfacts.domain.repository.NumberFactRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
abstract class NumberRepositoryModule {

    @Binds

    abstract fun bindRemoteDataSource(datasource: NumberFactRemoteDatasourceImpl): NumberFactRemoteDatasource

    @Binds
    abstract fun bindCacheDataSource(datasource: NumberFactCacheDatasourceImpl): NumberFactCacheDatasource

    @Binds
    abstract fun bindRepository(repository: NumberFactRepositoryImpl): NumberFactRepository

    companion object {
        @Provides
        fun provideApi(retrofit: Retrofit) = retrofit.create<NumberFactApi>()

        @Provides
        fun provideDao(db: AppDB) = db.numberFactDao
    }
}





