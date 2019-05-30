package com.mvvm.kanban_board.data.apiService

import retrofit2.Retrofit

object ApiUtils {

    val BASE_URL = "https://kanbanboardpolsl.herokuapp.com"

    val apiService: ApiService
        get() = RetrofitClient.getClient(BASE_URL)!!.create(ApiService::class.java)
}
