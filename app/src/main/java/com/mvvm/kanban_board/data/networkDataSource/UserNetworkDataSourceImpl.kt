package com.mvvm.kanban_board.data.networkDataSource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mvvm.kanban_board.data.apiService.ApiUtils
import com.mvvm.kanban_board.data.apiService.request.UserRegister
import com.mvvm.kanban_board.data.apiService.response.UserRegisterResponse
import kotlinx.coroutines.Deferred
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserNetworkDataSourceImpl (private val apiUtils: ApiUtils):UserNetworkDataSource{

//    private val _request = MutableLiveData<String>()
//    override val request: LiveData<String>
//        get() = _request

    override suspend fun registerUser(name: String, password: String):String{
        var message=""
        apiUtils.apiService.postUserAsync(UserRegister(username = name, password = password)).enqueue(object : Callback<UserRegisterResponse> {
            override fun onFailure(call: Call<UserRegisterResponse>, t: Throwable) {
                //_request.postValue("An error occurred, check the internet connection")
                message = "An error occurred, check the internet connection"
            }
            override fun onResponse(call: Call<UserRegisterResponse>, response: Response<UserRegisterResponse>) {
                if (response.isSuccessful) {
                    //_request.postValue("Your account was created successfully!")
                    message = "Your account was created successfully!"
                } else {
                    //_request.postValue("Sorry, server error occurred, try again")

                    val error = JSONObject(response.errorBody()?.string())
                        if(error.has("username")){
                            message = error.getString("username").removePrefix("[\"").removeSuffix("\"]")
                        } else{
                            message = "Sorry, server error occurred, try again"
                        }

                }
            }
        })
        return message
    }

}