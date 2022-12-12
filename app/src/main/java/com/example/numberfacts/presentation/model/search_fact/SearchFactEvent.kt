package com.example.numberfacts.presentation.model.search_fact

import com.example.numberfacts.domain.model.NumberFactModel

sealed interface SearchFactEvent {
    data class Error(val message: String?) : SearchFactEvent
    data class DetailsScreen(val model: NumberFactModel) : SearchFactEvent
}