package com.app.testapp

import retrofit2.http.GET

interface RetrofitApi {

    @GET("users?page=2")
    suspend fun fetchData(): ResultData

}