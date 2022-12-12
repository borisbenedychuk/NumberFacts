package com.example.numberfacts.presentation.screen.base

import io.reactivex.rxjava3.core.Observable

typealias Reducer<STATE, EVENT, EFFECT> = (STATE, EFFECT) -> Pair<STATE, EVENT?>

typealias Actor<STATE, INTENT, EFFECT> = (STATE, INTENT) -> Observable<out EFFECT>
