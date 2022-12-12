package com.example.numberfacts.application

import android.app.Application
import com.example.numberfacts.application.di.DaggerAppComponent

class App : Application() {
    val component by lazy { DaggerAppComponent.factory().create(this) }
}