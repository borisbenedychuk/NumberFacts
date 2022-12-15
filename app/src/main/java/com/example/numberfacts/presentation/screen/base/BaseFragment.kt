package com.example.numberfacts.presentation.screen.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseFragment : Fragment {
    constructor() : super()
    constructor(@LayoutRes layoutRes: Int) : super(layoutRes)

    private val compositeDisposable = CompositeDisposable()

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }

    fun <T : Any> Observable<T>.subscribeWithLifecycle(block: (T) -> Unit) {
        val lifecycle = viewLifecycleOwner.lifecycle
        subscribe { if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) block(it) }
            .also(compositeDisposable::add)
        lifecycle.addObserver(
            object : LifecycleEventObserver {
                override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                    if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
                        lifecycle.removeObserver(this)
                    }
                }
            }
        )
    }
}