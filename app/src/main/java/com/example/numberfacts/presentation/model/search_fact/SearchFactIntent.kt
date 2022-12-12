package com.example.numberfacts.presentation.model.search_fact

import com.example.numberfacts.domain.model.NumberFactModel

sealed interface SearchFactIntent {
    object SearchNumber : SearchFactIntent
    object SearchRandomNumber : SearchFactIntent
    object RequestHistory : SearchFactIntent
    data class ChangeText(val text: String) : SearchFactIntent
    data class PickHistory(val model: NumberFactModel) : SearchFactIntent
}