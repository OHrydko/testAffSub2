package com.example.testaffsub2.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.testaffsub2.App
import com.example.testaffsub2.model.Data
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Repository {
    private val listOfUser: MutableLiveData<Data> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getUsers(app: App, page: Int): MutableLiveData<Data>? {
        app.component.getApi().getUser(20, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                    data: Data? -> listOfUser.value = data
            }
        return listOfUser
    }
}