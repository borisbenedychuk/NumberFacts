package com.example.numberfacts.domain.use_case

import com.example.numberfacts.domain.repository.NumberRepository
import javax.inject.Inject

class GetSearchHistoryUseCase @Inject constructor(
    private val repository: NumberRepository
) {

    fun execute() = repository.getSearchHistory()
}