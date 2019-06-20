package com.mvvm.kanban_board.data.networkDataSource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm.kanban_board.data.apiService.ApiUtils
import com.mvvm.kanban_board.data.apiService.request.UserRequest
import com.mvvm.kanban_board.data.apiService.response.UserRegisterResponse
import com.mvvm.kanban_board.session.AuthenticationState
import com.mvvm.kanban_board.session.SessionManager

import org.json.JSONObject


class UserNetworkDataSourceImpl(private val apiUtils: ApiUtils) : UserNetworkDataSource {

    private val _authenticationState = MutableLiveData<AuthenticationState>()
    override val authenticationState: LiveData<AuthenticationState>
        get() = _authenticationState


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
        var message = ""
        try {
            apiUtils.apiService.getLoginTokenAsync(UserRequest(username = name, password = password)).let {
                if (it.isSuccessful) {
                     SessionManager.accessToken = it.body()?.token
                     SessionManager.username = name
                    getAllUsersAsync()?.find { u -> u.username == SessionManager.username  }.let{
                        SessionManager.userID = it?.id.toString()
                    }
                     _authenticationState.postValue(AuthenticationState.AUTHENTICATED)
                } else {
                        message = "Incorrect creditionals"
                        _authenticationState.postValue(AuthenticationState.UNAUTHENTICATED)
                 }
            }
        } catch (e: Exception) {
            message = "An error occurred, check the internet connection"
            _authenticationState.postValue(AuthenticationState.UNAUTHENTICATED)
        }
        return message
    }

    //temporary getting users list to check creditionals
    private suspend fun getAllUsersAsync(): List<UserRegisterResponse>?{
        try {
            apiUtils.apiServiceAuth.getAllUsersAsync().let{
                return it.body()
            }
        }
        catch (e: Exception) {
        }
        return null
    }



}