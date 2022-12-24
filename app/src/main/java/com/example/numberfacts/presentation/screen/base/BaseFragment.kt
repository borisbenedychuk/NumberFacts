package com.example.numberfacts.presentation.screen.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.State.DESTROYED
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseFragment : Fragment {
    constructor() : super()
    constructor(@LayoutRes layoutRes: Int) : super(layoutRes)

    private val compositeDisposable = CompositeDisposable()

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    fun <T : Any, R : Any> Observable<T>.handleState(
        scope: T.() -> R,
        handling: (R) -> Unit
    ) = this.map { it.scope() }
        .distinctUntilChanged()
        .subscribeWithLifecycle(handling)

    fun <T : Any> Observable<T>.subscribeWithLifecycle(block: (T) -> Unit) {
        val lifecycle = viewLifecycleOwner.lifecycle
        lifecycle.addObserver(
            object : LifecycleEventObserver {
                override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                    when (lifecycle.currentState) {
                        STARTED -> subscribe(block).also(compositeDisposable::add)
                        DESTROYED -> lifecycle.removeObserver(this)
                        else -> Unit
                    }
                }
            }
        )
    }
}