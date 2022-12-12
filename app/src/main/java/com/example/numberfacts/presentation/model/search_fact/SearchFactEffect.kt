package com.example.numberfacts.presentation.model.search_fact

import com.example.numberfacts.domain.model.NumberFactModel

sealed interface SearchFactEffect {
    data class DisplayFact(val model: NumberFactModel) : SearchFactEffect
    data class History(val models: List<NumberFactModel>) : SearchFactEffect
    data class TextChanged(val text: String) : SearchFactEffect
    data class Error(val message: String) : SearchFactEffect
    object Loading : SearchFactEffect
}