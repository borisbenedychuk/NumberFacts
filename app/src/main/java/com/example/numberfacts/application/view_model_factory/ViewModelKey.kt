package com.example.numberfacts.application.view_model_factory

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class ViewModelKey(val clazz: KClass<out ViewModel>)