package com.example.numberfacts.data.datasource

import com.example.numberfacts.data.cache.dao.NumberDao
import com.example.numberfacts.data.cache.model.NumberFactEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class NumberCacheDatasourceImpl @Inject constructor(
    private val dao: NumberDao,
) : NumberCacheDatasource {

    override fun getRecentNumberFacts(): Observable<List<NumberFactEntity>> = dao.getEntities()

    override fun saveNumberFact(entity: NumberFactEntity): Completable = dao.insertEntity(entity)
}