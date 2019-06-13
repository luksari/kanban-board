package com.mvvm.kanban_board.data.networkDataSource

import com.mvvm.kanban_board.data.apiService.ApiUtils
import com.mvvm.kanban_board.data.apiService.request.UserRequest
import com.mvvm.kanban_board.session.SessionManager

import org.json.JSONObject


class UserNetworkDataSourceImpl(private val apiUtils: ApiUtils) : UserNetworkDataSource {


    override suspend fun registerUser(name: String, password: String): String {
        var message: String
        try {
            apiUtils.apiService.postUserAsync(UserRequest(username = name, password = password)).let {
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

    override suspend fun loginUser(name: String, password: String): String {
        var message: String
        try {
            apiUtils.apiService.getLoginTokenAsync(UserRequest(username = name, password = password)).let {
                 if (it.isSuccessful) {
                     message = "Logged in!"
                     SessionManager.accessToken = it.body()?.token
                     SessionManager.username = name
                     getAllUsersAsync() //
                } else {
                        message = "Incorrect creditionals"
                }
            }
        } catch (e: Exception) {
            message = "An error occurred, check the internet connection"
        }
        return message
    }

    //temporary getting users list to check creditionals
    private suspend fun getAllUsersAsync() {
        try {
            apiUtils.apiService.getAllUsersAsync()
        }
        catch (e: Exception) {
        }
    }


}