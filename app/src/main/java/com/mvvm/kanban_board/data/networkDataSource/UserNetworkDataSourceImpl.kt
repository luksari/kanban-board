package com.mvvm.kanban_board.data.networkDataSource

import android.util.Log
import com.mvvm.kanban_board.data.apiService.ApiUtils
import com.mvvm.kanban_board.data.apiService.request.UserRegister
import com.mvvm.kanban_board.data.apiService.response.UserRegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserNetworkDataSourceImpl (private val apiUtils: ApiUtils):UserNetworkDataSource{
    override suspend fun registerUser(password: String, name: String){
        apiUtils.apiService.postUser(UserRegister(password, name)).enqueue(object : Callback<UserRegisterResponse> {
            override fun onFailure(call: Call<UserRegisterResponse>, t: Throwable) {
                Log.i("API", "failure" + t.message)
            }

            override fun onResponse(call: Call<UserRegisterResponse>, response: Response<UserRegisterResponse>) {

                if (response.isSuccessful()) {
                    Log.i("API", "successful response" + response.body().toString())
                    Log.i("API", "id returned by API" + response?.body()?.id)
                } else{ Log.i("API", "not succesfull, message: " + response?.message()) }
            }

        })
    }

}