package com.mvvm.kanban_board.data.repo

import android.content.Context
import androidx.lifecycle.LiveData
import com.mvvm.kanban_board.data.db.entity.User
import com.mvvm.kanban_board.session.AuthenticationState

interface Repository {

    //API
    suspend fun registerNewUser(name: String, password: String): String?
    suspend fun loginUser(name: String, password: String): String?

    val authenticationState: LiveData<AuthenticationState>
}