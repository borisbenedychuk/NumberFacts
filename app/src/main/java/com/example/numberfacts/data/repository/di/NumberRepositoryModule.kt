package com.example.numberfacts.data.repository.di

import com.example.numberfacts.data.cache.AppDB
import com.example.numberfacts.data.datasource.NumberCacheDatasource
import com.example.numberfacts.data.datasource.NumberCacheDatasourceImpl
import com.example.numberfacts.data.datasource.NumberRemoteDatasource
import com.example.numberfacts.data.datasource.NumberRemoteDatasourceImpl
import com.example.numberfacts.data.remote.api.NumberApi
import com.example.numberfacts.data.repository.NumberRepositoryImpl
import com.example.numberfacts.domain.repository.NumberRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
abstract class NumberRepositoryModule {

    @Binds

    abstract fun bindRemoteDataSource(datasource: NumberRemoteDatasourceImpl): NumberRemoteDatasource

    @Binds
    abstract fun bindCacheDataSource(datasource: NumberCacheDatasourceImpl): NumberCacheDatasource

    @Binds
    abstract fun bindRepository(repository: NumberRepositoryImpl): NumberRepository

    companion object {
        @Provides
        fun provideApi(retrofit: Retrofit) = retrofit.create<NumberApi>()

        @Provides
        fun provideDao(db: AppDB) = db.numberDao
    }
}





