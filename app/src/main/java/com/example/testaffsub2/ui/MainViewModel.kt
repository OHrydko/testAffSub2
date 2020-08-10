package com.example.testaffsub2.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testaffsub2.App
import com.example.testaffsub2.model.Data
import com.example.testaffsub2.repository.Repository

class MainViewModel : ViewModel() {
    private var repository: Repository? = null
    private lateinit var app: App
    var isLoader: MutableLiveData<Boolean> = MutableLiveData()

    fun init(app: App) {
        repository = Repository()
        this.app = app
    }

    fun getUsers(page: Int): MutableLiveData<Data>? {
        return repository?.getUsers(app, page)
    }
}