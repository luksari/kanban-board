package com.mvvm.kanban_board.data.Repo

import android.util.Log
import androidx.lifecycle.LiveData
import com.mvvm.kanban_board.data.db.KanbanDao
import com.mvvm.kanban_board.data.db.entity.User

class RepositoryImpl(
    private val kanbanDao: KanbanDao
) :Repository {

    override suspend fun getUserByName(): LiveData<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun test(){
        Log.d("BAZA", "repo is INITIALIZED")
    }
    init{
        //check if the user is logged -> local db
        //sign in/sign up -> rest api
    }
}