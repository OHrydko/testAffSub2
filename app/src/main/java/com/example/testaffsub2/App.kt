package com.example.testaffsub2

import android.app.Application
import com.example.testaffsub2.di.Component
import com.example.testaffsub2.di.DaggerComponent
import com.example.testaffsub2.di.RetrofitModule


class App : Application() {

    lateinit var component: Component

    override fun onCreate() {
        super.onCreate()
        component = initDagger()
    }

    private fun initDagger(): Component =
        DaggerComponent.builder()
            .retrofitModule(RetrofitModule())
            .build()
}