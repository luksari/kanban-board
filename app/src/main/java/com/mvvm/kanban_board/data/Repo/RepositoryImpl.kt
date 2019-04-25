package com.mvvm.kanban_board.data.Repo

import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.mvvm.kanban_board.data.db.KanbanDao
import com.mvvm.kanban_board.data.db.entity.*
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.InputStream
import java.nio.charset.Charset
import java.nio.file.Paths

class RepositoryImpl(
    private val kanbanDao: KanbanDao

) :Repository {


    override suspend fun getUserByName(): LiveData<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    init {
        //val inputStringBoards = inputStreamBoards.bufferedReader().use{it.readText()}
        //Log.d("BAZA", inputStringBoards)
        //initializeBoardsFromJSON()
    }

        //  val User = Gson().fromJson(, Article::class.java)
        //check if the user is logged in vm -> local db
        //sign in/sign up -> rest api
        // }
  override fun initializeFakeData(context: Context){
            initializeBoardsFromJSON(context)
        }

//    fun initializeUsersFromJSON() {
//        try {
//            val inputStreamUsers: InputStream = appContext.resources.assets.open("data_users.json")
//
//            //val inputStreamUsers: InputStream = appContext.assets.open("data_users.json")
//            val inputStringUsers = inputStreamUsers.bufferedReader().use { it.readText() }
//            var users = Gson().fromJson(inputStringUsers, UsersResponse::class.java)
//            for (u in users.users!!) {
//                Log.d("BAZA", u?.name)
//            }
//        }catch (e:Exception){ }
//        }
//
//
    fun initializeBoardsFromJSON(context: Context){
        try {
            val inputStreamBoards: InputStream =  context.assets.open("data_boards.json")
            val inputStringBoards = inputStreamBoards.bufferedReader().use{it.readText()}
            var board = Gson().fromJson(inputStringBoards, BoardsResponse::class.java)
            for (b in board.boards!!) {
                Log.d("BAZA", b?.name)
            }
        } catch (e:Exception){
            Log.d("BAZA",e.message)
        }
    }

}