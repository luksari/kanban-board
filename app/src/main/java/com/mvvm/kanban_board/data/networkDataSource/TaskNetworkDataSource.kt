package com.mvvm.kanban_board.data.networkDataSource

import com.mvvm.kanban_board.data.apiService.response.BoardResponse
import com.mvvm.kanban_board.data.apiService.response.TaskResponse

interface TaskNetworkDataSource {

    suspend fun addTaskToPage(name: String, ownerID: Long, description: String, pageID: Long): String
    suspend fun loadAllTasks(): List<TaskResponse>?
    suspend fun loadPageTasks(pageID: Long): List<TaskResponse>?
    suspend fun deleteTasks(taskID: Long): String?
    suspend fun editTasks(taskID: Long, name: String, ownerID: Long, description: String, pageID: Long): String?
    suspend fun loadTask(taskID: Long): TaskResponse?


}