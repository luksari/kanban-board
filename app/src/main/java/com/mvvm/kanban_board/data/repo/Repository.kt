package com.mvvm.kanban_board.data.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm.kanban_board.data.apiService.response.BoardResponse
import com.mvvm.kanban_board.data.apiService.response.PageResponse
import com.mvvm.kanban_board.data.apiService.response.TaskResponse
import com.mvvm.kanban_board.data.apiService.response.UserRegisterResponse
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

    suspend fun addTaskToPage(pageName :String): String?
    suspend fun addTaskToPage(name: String, ownerID: Long, description: String, pageID: Long): String?
    suspend fun loadPageTasks(pageName: String): List<TaskResponse>?

    suspend fun deleteTask(taskID: Long): String?
    suspend fun editTask(taskID: Long, name: String, ownerID: Long, description: String, pageID: Long): String?
    suspend fun loadTask(taskID: Long)

    suspend fun loadUser(userID: Long?): UserRegisterResponse?

    suspend fun loadSelectedTask(): TaskResponse?
    suspend fun deleteSelectedTask(): String?
    suspend fun editSelectedTask(name: String? = "", description: String? = ""): String?





    val authenticationState: LiveData<AuthenticationState>
    val currentBoard: LiveData<BoardResponse>
    val currentBoardPages: LiveData<List<PageResponse>>
    val selectedTaskID: MutableLiveData<Long>
}