package com.mvvm.kanban_board.data.Repo

import android.util.Log
import androidx.annotation.RestrictTo
import androidx.lifecycle.LiveData
import com.mvvm.kanban_board.data.db.KanbanDao
import com.mvvm.kanban_board.data.db.entity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RepositoryImpl(
    private val kanbanDao: KanbanDao
) :Repository {

    override suspend fun getUserByName(): LiveData<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkRepoInjection(){
        Log.d("REPO", "repo is already initialized")
    }
    init{


        //check if the user is logged in vm -> local db
        //sign in/sign up -> rest api
    }


}