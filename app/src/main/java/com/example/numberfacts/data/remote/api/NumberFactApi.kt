package com.example.numberfacts.data.remote.api

import com.example.numberfacts.data.remote.model.NumberFactResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface NumberFactApi {

    @GET("{number}")
    fun getNumberFact(@Path("number") number: Int): Observable<NumberFactResponse>

    @GET("random")
    fun getRandomNumberFact(): Observable<NumberFactResponse>
}