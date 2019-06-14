package com.mvvm.kanban_board.data.networkDataSource

import androidx.lifecycle.LiveData
import com.mvvm.kanban_board.session.AuthenticationState


interface UserNetworkDataSource {
    suspend fun registerUser(name: String, password: String): String
    suspend fun loginUser(name: String, password: String) : String
    val authenticationState: LiveData<AuthenticationState>
    val responseMessage: LiveData<String>
}