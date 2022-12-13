package com.example.numberfacts.data.datasource

import com.example.numberfacts.data.remote.api.NumberFactApi
import com.example.numberfacts.data.remote.model.NumberFactResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class NumberFactRemoteDatasourceImpl @Inject constructor(
    private val api: NumberFactApi,
) : NumberFactRemoteDatasource {

    override fun getRandomNumberFact(): Observable<NumberFactResponse> = api.getRandomNumberFact()

    override fun getNumberFact(number: Int): Observable<NumberFactResponse> =
        api.getNumberFact(number)
}