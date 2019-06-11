package com.mvvm.kanban_board.data.networkDataSource

import androidx.lifecycle.LiveData
import com.mvvm.kanban_board.data.apiService.ApiService
import com.mvvm.kanban_board.data.db.entity.User

interface UserNetworkDataSource{
    suspend fun registerUser(name: String, password: String): String
   //val request: LiveData<String>
}