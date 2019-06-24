package com.mvvm.kanban_board.data.repo

import android.content.Context
import androidx.lifecycle.LiveData
import com.mvvm.kanban_board.data.apiService.response.BoardResponse
import com.mvvm.kanban_board.data.db.entity.Board
import com.mvvm.kanban_board.data.db.entity.User
import com.mvvm.kanban_board.session.AuthenticationState

interface Repository {

    //API
    suspend fun registerNewUser(name: String, password: String): String?
    suspend fun loginUser(name: String, password: String): String?
    suspend fun createBoard(identifier: String, name: String): String?
    suspend fun enterBoard(identifier: String): String?
    suspend fun loadBoardPages()

    suspend fun addTaskToPage(name: String, ownerID: Long, description: String, pageID: Long): String?
    suspend fun loadPageTasks(pageID: Long)

    val authenticationState: LiveData<AuthenticationState>
    val currentBoard: LiveData<BoardResponse>
}