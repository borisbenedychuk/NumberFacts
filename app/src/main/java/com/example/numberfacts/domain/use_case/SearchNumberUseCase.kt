package com.example.numberfacts.domain.use_case

import com.example.numberfacts.domain.model.NumberFactModel
import com.example.numberfacts.domain.repository.NumberRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class SearchNumberUseCase @Inject constructor(
    private val repository: NumberRepository,
) {

    fun execute(number: Int): Observable<NumberFactModel> = repository.getNumberFact(number)
}