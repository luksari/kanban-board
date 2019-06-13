package com.mvvm.kanban_board.data.apiService

import android.preference.PreferenceManager.getDefaultSharedPreferences
import retrofit2.Retrofit

object ApiUtils {

    const val BASE_URL = "https://kanbanboardpolsl.herokuapp.com"

    val apiService: ApiService
        get() = RetrofitClient.getClient()!!.create(ApiService::class.java)

}
