package com.example.numberfacts.presentation.screen.base

import androidx.lifecycle.ViewModel
import com.example.numberfacts.presentation.utils.update
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject

abstract class BaseViewModel<STATE : Any, EVENT : Any, ACTION : Any, EFFECT : Any>(
    initialState: STATE,
    private val reducer: Reducer<STATE, EVENT, EFFECT>,
    private val actor: Actor<STATE, ACTION, EFFECT>,
) : ViewModel() {

    private val _state = BehaviorSubject.createDefault(initialState)
    private val _events = PublishSubject.create<EVENT>()

    val events: Observable<EVENT> = _events.observeOn(AndroidSchedulers.mainThread())
    val state: Observable<STATE> = _state.observeOn(AndroidSchedulers.mainThread())

    private val intentSubject = PublishSubject.create<ACTION>()

    private val compositeDisposable = CompositeDisposable()

    fun pushAction(action: ACTION) = intentSubject.onNext(action)

    init {
        intentSubject
            .flatMap { intent -> actor.invoke(_state.value!!, intent) }
            .subscribeAndSaveDisposable { effect ->
                val (state, event) = reducer.invoke(_state.value!!, effect)
                _state.update { state }
                event?.let { _events.onNext(it) }
            }
    }

    override fun onCleared() = compositeDisposable.dispose()

    private fun <T : Any> Observable<T>.subscribeAndSaveDisposable(block: (T) -> Unit) =
        subscribe(block).also(compositeDisposable::add)
}

