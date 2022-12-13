package com.example.numberfacts.data.datasource

import com.example.numberfacts.data.cache.dao.NumberFactDao
import com.example.numberfacts.data.cache.model.NumberFactEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers.io
import javax.inject.Inject

class NumberFactCacheDatasourceImpl @Inject constructor(
    private val dao: NumberFactDao,
) : NumberFactCacheDatasource {

    override fun getRecentNumberFacts(): Observable<List<NumberFactEntity>> = dao.getEntities()

    override fun saveNumberFact(entity: NumberFactEntity): Completable =
        Completable.create {
            dao.insertEntityAndTrimHistory(entity)
            it.onComplete()
        }.subscribeOn(io())
}