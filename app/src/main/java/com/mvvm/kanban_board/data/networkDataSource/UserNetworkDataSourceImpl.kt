package com.mvvm.kanban_board.data.networkDataSource

import android.util.Log
import com.mvvm.kanban_board.data.apiService.ApiUtils
import com.mvvm.kanban_board.data.apiService.request.UserRegister
import com.mvvm.kanban_board.data.apiService.response.UserRegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserNetworkDataSourceImpl (private val apiUtils: ApiUtils):UserNetworkDataSource{
    override suspend fun registerUser(name: String, password: String){
        apiUtils.apiService.postUser(UserRegister(username = name, password = password)).enqueue(object : Callback<UserRegisterResponse> {
            override fun onFailure(call: Call<UserRegisterResponse>, t: Throwable) {
                Log.i("API", "FAILED: " + t.message)
            }

            override fun onResponse(call: Call<UserRegisterResponse>, response: Response<UserRegisterResponse>) {
                if (response.isSuccessful) {
                    Log.i("API", "name returned by API: " + response.body()?.username)
                    Log.i("API", "id returned by API: " + response.body()?.id)
                } else {
                     Log.i("API", "not succesfull, message: " + response.errorBody().toString())
                }
            }
            
        })
    }


}