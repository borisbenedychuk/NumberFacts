package com.example.numberfacts.presentation.model.search_fact

import com.example.numberfacts.domain.model.NumberFactModel

data class SearchFactState(
    val history: List<NumberFactModel> = emptyList(),
    val number: String = "",
    val loading: Boolean = false,
)