package com.example.numberfacts.presentation.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <A : RecyclerView.Adapter<*>> Fragment.adapters(
    recyclerView: () -> RecyclerView,
    initializer: () -> A,
) = AdaptersDelegate(initializer, recyclerView)

class AdaptersDelegate<A : RecyclerView.Adapter<*>>(
    private val initializer: () -> A,
    private val recyclerView: () -> RecyclerView,
) : ReadOnlyProperty<Fragment, A> {

    private var adapter: A? = null
    private var observer: LifecycleEventObserver? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): A {
        adapter = adapter ?: initializer()
        if (observer == null) {
            val recyclerView = recyclerView().also { it.adapter = adapter }
            observer = object : LifecycleEventObserver {
                override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                    if (event == Lifecycle.Event.ON_DESTROY) {
                        recyclerView.adapter = null
                        thisRef.viewLifecycleOwner.lifecycle.removeObserver(this)
                        observer = null
                    }
                }
            }
        }
        thisRef.viewLifecycleOwner.lifecycle.addObserver(observer!!)
        return adapter!!
    }
}