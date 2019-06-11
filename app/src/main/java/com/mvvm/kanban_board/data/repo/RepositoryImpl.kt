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
    override suspend fun registerNewUser(name: String, password: String): String?{
        var passwordHashed = hashPassword(password)
        val message = userNetworkDataSource.registerUser(name, passwordHashed)
        Log.d("D/OkHttp mes", message)
        return message
        //return userNetworkDataSource.request.value
        //return userNetworkDataSource.registerUser(name, passwordHashed)
    }

    private fun hashPassword(password: String): String{
        var salt = BCrypt.gensalt(5)
        var hashed = BCrypt.hashpw(password, salt)
       // Log.d("haslo", BCrypt.checkpw(password, hashed).toString())
        return hashed
    }


}