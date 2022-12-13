package com.example.numberfacts.domain.use_case

import com.example.numberfacts.domain.repository.NumberFactRepository
import javax.inject.Inject

class GetSearchHistoryUseCase @Inject constructor(
    private val repository: NumberFactRepository
) {

    fun execute() = repository.getSearchHistory()
}