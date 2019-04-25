package com.mvvm.kanban_board.data.Repo

import android.content.Context
import androidx.lifecycle.LiveData
import com.mvvm.kanban_board.data.db.entity.User

interface Repository {
    suspend fun getUserByName(): LiveData<User>
    fun initializeFakeData(context: Context)

}