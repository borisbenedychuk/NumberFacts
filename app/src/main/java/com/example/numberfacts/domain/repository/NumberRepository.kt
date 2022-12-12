package com.example.numberfacts.domain.repository

import com.example.numberfacts.domain.model.NumberFactModel
import io.reactivex.rxjava3.core.Observable

interface NumberRepository {

    fun getRandomNumberFact(): Observable<NumberFactModel>

    fun getNumberFact(number: Int): Observable<NumberFactModel>

    fun getSearchHistory(): Observable<List<NumberFactModel>>
}