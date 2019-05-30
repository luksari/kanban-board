package com.mvvm.kanban_board.data.networkDataSource

import com.mvvm.kanban_board.data.apiService.ApiService
import com.mvvm.kanban_board.data.db.entity.User

interface UserNetworkDataSource{
    suspend fun registerUser(password: String, name: String) //: User
}