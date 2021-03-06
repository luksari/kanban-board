package com.mvvm.kanban_board.data.networkDataSource

import androidx.lifecycle.LiveData
import com.mvvm.kanban_board.data.apiService.response.UserRegisterResponse
import com.mvvm.kanban_board.session.AuthenticationState


interface UserNetworkDataSource {
    suspend fun registerUser(name: String, password: String): String
    suspend fun loginUser(name: String, password: String) : String
    suspend fun loadUser(userID: Long) : UserRegisterResponse?

    val authenticationState: LiveData<AuthenticationState>
}