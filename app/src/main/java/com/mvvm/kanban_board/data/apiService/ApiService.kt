package com.mvvm.kanban_board.data.apiService

import com.mvvm.kanban_board.data.apiService.request.UserRegister
import com.mvvm.kanban_board.data.apiService.response.UserRegisterResponse
import com.mvvm.kanban_board.data.db.entity.User
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/api/users")
    fun postUserAsync(@Body user: UserRegister): Call<UserRegisterResponse>
}