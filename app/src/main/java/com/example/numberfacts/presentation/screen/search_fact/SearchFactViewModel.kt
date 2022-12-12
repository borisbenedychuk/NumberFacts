package com.example.numberfacts.presentation.screen.search_fact

import com.example.numberfacts.domain.use_case.GetSearchHistoryUseCase
import com.example.numberfacts.domain.use_case.SearchNumberUseCase
import com.example.numberfacts.domain.use_case.SearchRandomNumberUseCase
import com.example.numberfacts.presentation.model.search_fact.SearchFactEffect
import com.example.numberfacts.presentation.model.search_fact.SearchFactEffect.*
import com.example.numberfacts.presentation.model.search_fact.SearchFactEvent
import com.example.numberfacts.presentation.model.search_fact.SearchFactIntent
import com.example.numberfacts.presentation.model.search_fact.SearchFactIntent.*
import com.example.numberfacts.presentation.model.search_fact.SearchFactState
import com.example.numberfacts.presentation.screen.base.BaseViewModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class SearchFactViewModel @Inject constructor(
    private val searchNumberUseCase: SearchNumberUseCase,
    private val searchRandomNumberUseCase: SearchRandomNumberUseCase,
    private val getSearchHistoryUseCase: GetSearchHistoryUseCase,
) : BaseViewModel<SearchFactState, SearchFactEvent, SearchFactIntent, SearchFactEffect>(
    initialState = SearchFactState(),
    actor = { state, intent ->
        val observable: Observable<SearchFactEffect> =
            when (intent) {
                is ChangeText -> Observable.just(TextChanged(intent.text))
                is PickHistory -> Observable.just(DisplayFact(intent.model))
                is SearchNumber ->
                    Observable.concat(
                        Observable.just(Loading),
                        searchNumberUseCase
                            .execute(state.number.toInt())
                            .map(::DisplayFact)
                    )
                is SearchRandomNumber ->
                    Observable.concat(
                        Observable.just(Loading),
                        searchRandomNumberUseCase
                            .execute()
                            .map(::DisplayFact)
                    )
                is RequestHistory ->
                    getSearchHistoryUseCase
                        .execute()
                        .map(::History)
            }
        observable.onErrorReturn { error ->
            error.printStackTrace()
            Error(error.message.orEmpty())
        }
    },
    reducer = { state, effect ->
        when (effect) {
            is TextChanged -> state.copy(number = effect.text) to null
            is Error -> state.copy(loading = false) to SearchFactEvent.Error(effect.message)
            is DisplayFact -> state.copy(loading = false) to SearchFactEvent.DetailsScreen(effect.model)
            is Loading -> state.copy(loading = true) to null
            is History -> state.copy(history = effect.models) to null
        }
    },
) {
    init {
        pushAction(RequestHistory)
    }
}


