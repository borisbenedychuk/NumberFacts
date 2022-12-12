package com.example.numberfacts.application.fragment_factory

import androidx.fragment.app.Fragment
import javax.inject.Inject
import javax.inject.Provider

class FragmentFactory @Inject constructor(
    private val providers: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<FragmentProvider>>
) : androidx.fragment.app.FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        providers[classLoader.loadClass(className)]?.get()?.fragment ?: super.instantiate(
            classLoader,
            className
        )
}