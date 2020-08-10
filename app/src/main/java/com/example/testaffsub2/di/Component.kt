package com.example.testaffsub2.di

import com.example.testaffsub2.network.ApiService
import dagger.Component


@Component(modules = [RetrofitModule::class])
interface Component {
    fun getApi(): ApiService
}