package com.mvvm.kanban_board.data.networkDataSource

import com.mvvm.kanban_board.data.apiService.response.BoardResponse
import com.mvvm.kanban_board.data.apiService.response.TaskResponse

interface TaskNetworkDataSource {

    suspend fun addTaskToPage(name: String, ownerID: Long, description: String, pageID: Long): String
    suspend fun loadAllTasks(): List<TaskResponse>?
    suspend fun loadPageTasks(pageID: Long): List<TaskResponse>?

}