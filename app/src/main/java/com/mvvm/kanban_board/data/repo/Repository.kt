package com.mvvm.kanban_board.data.repo

import android.content.Context
import androidx.lifecycle.LiveData
import com.mvvm.kanban_board.data.db.entity.User

interface Repository {

    //API
    suspend fun registerNewUser(name: String, password: String)


    suspend fun getUserByName(): LiveData<User>
    fun initializeFakeData(context: Context)

}