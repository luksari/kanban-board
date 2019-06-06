package com.mvvm.kanban_board.data.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.mvvm.kanban_board.data.db.KanbanDao
import com.mvvm.kanban_board.data.db.entity.*
import com.mvvm.kanban_board.data.db.response.UsersResponse
import com.mvvm.kanban_board.data.networkDataSource.UserNetworkDataSource
import kotlinx.coroutines.*
import org.mindrot.jbcrypt.BCrypt
import java.io.InputStream

class RepositoryImpl(
    private val kanbanDao: KanbanDao,
    private val userNetworkDataSource: UserNetworkDataSource
) :Repository {
    override suspend fun registerNewUser(name: String, password: String) {
        var passwordHashed = hashPassword(password)
        userNetworkDataSource.registerUser(name, passwordHashed)
    }

    private fun hashPassword(password: String): String{
        return ""

        var salt = BCrypt.gensalt(5)
        var hashed = BCrypt.hashpw(password, salt)



    }


    override suspend fun getUserByName(): LiveData<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    init {
        //check if the user is logged in vm -> local db
        //sign in/sign up -> rest api
    }


  override fun initializeFakeData(context: Context){
      GlobalScope.launch(Dispatchers.IO) {
          with(context){
              val usersResponse = initializeUsersFromJSON(this)
              val boardsResponse = initializeBoardsFromJSON(this)

              usersResponse?.users?.forEach{ it?.let{ user -> kanbanDao.upsertUser(user) } }
              boardsResponse?.boards?.forEach{ it?.let{ board -> kanbanDao.upsertBoard(board)} }
          }
         // Log.d("BAZA getBoard", kanbanDao.getBoardByName("Mock board").toString())
          Log.d("BAZA getUsers", kanbanDao.getUserByName("Justyna").toString())

      }

   }

    fun initializeUsersFromJSON(context: Context): UsersResponse? {
        return try {
            val inputStreamUsers: InputStream = context.assets.open("data_users.json")
            val inputStringUsers = inputStreamUsers.bufferedReader().use { it.readText() }
            return Gson().fromJson(inputStringUsers, UsersResponse::class.java)
        } catch (e: Exception) {null}
    }

    fun initializeBoardsFromJSON(context: Context): BoardsResponse?{
        return try {
            val inputStreamBoards: InputStream =  context.assets.open("data_boards.json")
            val inputStringBoards = inputStreamBoards.bufferedReader().use{it.readText()}
            return Gson().fromJson(inputStringBoards, BoardsResponse::class.java)
        } catch (e:Exception){null}
    }

}