package com.example.numberfacts.data.repository

import com.example.numberfacts.data.datasource.NumberFactCacheDatasource
import com.example.numberfacts.data.datasource.NumberFactRemoteDatasource
import com.example.numberfacts.data.remote.model.NumberFactResponse
import com.example.numberfacts.domain.model.NumberFactModel
import com.example.numberfacts.domain.repository.NumberFactRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class NumberFactRepositoryImpl @Inject constructor(
    private val numberFactCacheDatasource: NumberFactCacheDatasource,
    private val numberFactRemoteDatasource: NumberFactRemoteDatasource,
) : NumberFactRepository {

    override fun getRandomNumberFact(): Observable<NumberFactModel> =
        numberFactRemoteDatasource.getRandomNumberFact().saveToCache()

    override fun getNumberFact(number: Int): Observable<NumberFactModel> =
        numberFactRemoteDatasource.getNumberFact(number).saveToCache()

    private fun Observable<NumberFactResponse>.saveToCache() = flatMap { response ->
        val entity = response.asEntity()
        numberFactCacheDatasource.saveNumberFact(entity).andThen(Observable.just(entity.asModel()))
    }

    override fun getSearchHistory(): Observable<List<NumberFactModel>> =
        numberFactCacheDatasource.getRecentNumberFacts().map { list -> list.map { it.asModel() } }
}