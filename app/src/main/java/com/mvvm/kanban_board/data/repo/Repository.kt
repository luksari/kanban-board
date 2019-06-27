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
    suspend fun addTaskToPage(): String?
    suspend fun loadPageTasks(pageName: String): List<TaskResponse>?
    suspend fun loadUser(userID: Long?): UserRegisterResponse?
   // suspend fun loadSelectedTask(): TaskResponse?
    suspend fun deleteSelectedTask(): String?
    suspend fun editTask(editedTask: TaskResponse): String?
    suspend fun setCurrentTask(taskID: Long)




    val authenticationState: LiveData<AuthenticationState>
    val currentBoard: LiveData<BoardResponse>
    val currentBoardPages: LiveData<List<PageResponse>>
    val currentPage: LiveData<PageResponse>
    val currentTask: LiveData<TaskResponse>

    // val selectedTaskID: MutableLiveData<Long>

    //val curentTaskID: MutableLiveData<Long>

}