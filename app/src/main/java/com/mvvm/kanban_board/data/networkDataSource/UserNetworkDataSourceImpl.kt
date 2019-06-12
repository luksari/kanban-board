package com.mvvm.kanban_board.data.networkDataSource

import com.mvvm.kanban_board.data.apiService.ApiUtils
import com.mvvm.kanban_board.data.apiService.request.UserRegister

import org.json.JSONObject


class UserNetworkDataSourceImpl(private val apiUtils: ApiUtils) : UserNetworkDataSource {


    override suspend fun registerUser(name: String, password: String): String {
        var message: String
        try {
            apiUtils.apiService.postUserAsync(UserRegister(username = name, password = password)).let {
                message = if (it.isSuccessful) {
                    "Your account was created successfully!"
                } else {
                    val error = JSONObject(it.errorBody()?.string())
                    if (error.has("username")) {
                        error.getString("username").removePrefix("[\"").removeSuffix("\"]")
                    } else {
                        "Sorry, server error occurred, try again"
                    }
                }
            }
        } catch (e: Exception) {
            message = "An error occurred, check the internet connection"
        }
        return message
    }


}