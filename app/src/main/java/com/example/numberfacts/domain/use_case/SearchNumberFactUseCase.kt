package com.example.numberfacts.domain.use_case

import com.example.numberfacts.domain.model.NumberFactModel
import com.example.numberfacts.domain.repository.NumberFactRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class SearchNumberFactUseCase @Inject constructor(
    private val repository: NumberFactRepository,
) {

    fun execute(number: Int): Observable<NumberFactModel> = repository.getNumberFact(number)
}