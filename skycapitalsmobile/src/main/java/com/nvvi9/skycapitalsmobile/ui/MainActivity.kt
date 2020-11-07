package com.nvvi9.skycapitalsmobile.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nvvi9.skycapitalsmobile.R
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}