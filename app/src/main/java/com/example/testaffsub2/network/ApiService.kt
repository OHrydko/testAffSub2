package com.example.testaffsub2.network

import com.example.testaffsub2.model.Data
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/")
    fun getUser(
        @Query("results") result: Int,
        @Query("page") page: Int
    ): Observable<Data>
}