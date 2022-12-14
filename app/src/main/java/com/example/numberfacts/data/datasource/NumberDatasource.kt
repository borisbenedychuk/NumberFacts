package com.example.numberfacts.data.datasource

import com.example.numberfacts.data.cache.model.NumberFactEntity
import com.example.numberfacts.data.remote.model.NumberFactResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface NumberFactCacheDatasource {

    fun getRecentNumberFacts(): Observable<List<NumberFactEntity>>

    fun saveNumberFact(entity: NumberFactEntity): Completable
}

interface NumberFactRemoteDatasource {

    fun getRandomNumberFact(): Observable<NumberFactResponse>

    fun getNumberFact(number: Int): Observable<NumberFactResponse>
}