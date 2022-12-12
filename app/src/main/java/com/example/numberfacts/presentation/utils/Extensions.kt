package com.example.numberfacts.presentation.utils

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject


inline fun <reified T : Any> Observable<*>.filterIsInstance(): Observable<T> =
    this.filter { it is T }.cast(T::class.java)

inline fun <T> BehaviorSubject<T>.update(block: (T) -> T) = value?.let { onNext(block(it)) }
