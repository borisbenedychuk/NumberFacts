package com.example.numberfacts.data.repository

import com.example.numberfacts.data.datasource.NumberCacheDatasource
import com.example.numberfacts.data.datasource.NumberRemoteDatasource
import com.example.numberfacts.data.remote.model.NumberFactResponse
import com.example.numberfacts.domain.model.NumberFactModel
import com.example.numberfacts.domain.repository.NumberRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class NumberRepositoryImpl @Inject constructor(
    private val numberCacheDatasource: NumberCacheDatasource,
    private val numberRemoteDatasource: NumberRemoteDatasource,
) : NumberRepository {

    override fun getRandomNumberFact(): Observable<NumberFactModel> =
        numberRemoteDatasource.getRandomNumberFact().saveToCache()

    override fun getNumberFact(number: Int): Observable<NumberFactModel> =
        numberRemoteDatasource.getNumberFact(number).saveToCache()

    private fun Observable<NumberFactResponse>.saveToCache() = flatMap { response ->
        val entity = response.asEntity()
        numberCacheDatasource.saveNumberFact(entity).andThen(Observable.just(entity.asModel()))
    }

    override fun getSearchHistory(): Observable<List<NumberFactModel>> =
        numberCacheDatasource.getRecentNumberFacts().map { list -> list.map { it.asModel() } }
}