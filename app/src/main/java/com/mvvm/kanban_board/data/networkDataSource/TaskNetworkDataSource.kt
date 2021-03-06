package com.mvvm.kanban_board.data.networkDataSource

import androidx.lifecycle.LiveData
import com.mvvm.kanban_board.data.apiService.response.BoardResponse
import com.mvvm.kanban_board.data.apiService.response.TaskResponse

interface TaskNetworkDataSource {

    suspend fun addTaskToPage(name: String, ownerID: Long, description: String, pageID: Long): TaskResponse?
    suspend fun loadAllTasks(): List<TaskResponse>? //LiveData<List<TaskResponse>>?
    suspend fun loadPageTasks(pageID: Long?): List<TaskResponse>?// LiveData<List<TaskResponse>>?
    suspend fun deleteTasks(taskID: Long): String?
    suspend fun editTasks(taskID: Long, name: String, ownerID: Long, description: String, pageID: Long): String?
    suspend fun loadTask(taskID: Long): TaskResponse?


    suspend fun editTasks(editedTask: TaskResponse): String?
}