package com.example.numberfacts.application.di

import android.content.Context
import androidx.room.Room
import com.example.numberfacts.data.cache.AppDB
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class CommonModule {

    @Provides
    @Singleton
    fun provideDB(context: Context): AppDB =
        Room.databaseBuilder(
            context,
            AppDB::class.java,
            "app_db",
        ).build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://numbersapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor {
                it.proceed(
                    it.request()
                        .newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .build()
                )
            }
            .build()
}