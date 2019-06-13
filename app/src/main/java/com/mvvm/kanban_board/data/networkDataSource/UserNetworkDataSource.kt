package com.mvvm.kanban_board.data.networkDataSource


interface UserNetworkDataSource {
    suspend fun registerUser(name: String, password: String): String
    suspend fun loginUser(name: String, password: String): String
}