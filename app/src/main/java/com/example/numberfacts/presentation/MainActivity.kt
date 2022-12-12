package com.example.numberfacts.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.numberfacts.R
import com.example.numberfacts.application.App

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory =
            (applicationContext as App).component.fragmentFactory
        super.onCreate(savedInstanceState)
    }
}