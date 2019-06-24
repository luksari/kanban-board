package com.mvvm.kanban_board.data.networkDataSource
import com.mvvm.kanban_board.data.apiService.ApiUtils
import com.mvvm.kanban_board.data.apiService.request.TaskRequest
import com.mvvm.kanban_board.data.apiService.response.TaskResponse

class TaskNetworkDataSourceImpl(private val apiUtils: ApiUtils) : TaskNetworkDataSource {

    override suspend fun addTaskToPage(name: String, ownerID: Long, description: String, pageID: Long): String {
        var message: String
        try {
            apiUtils.apiService.postTaskToPageAsync(TaskRequest(name = name, user = ownerID, description = description, page = pageID) ).let {
                message = if (it.isSuccessful) {
                    "Your board was created successfully!"
                } else { "Sorry, server error occurred, try again"
                }
            }
        } catch (e: Exception) {
            message = "An error occurred, check the internet connection"
        }
        return message
    }


    override suspend fun loadAllTasks(): List<TaskResponse>? {
        try{
            apiUtils.apiService.getAllTasksAsync().let{
                if(it.isSuccessful) return it.body()
            }
        }catch(e: Exception){
            //internet problems?
        }
        return null
    }

    override suspend fun loadPageTasks(pageID: Long): List<TaskResponse>? {
        loadAllTasks()?.let{
            return it.filter { t -> t.page == pageID }}
        return null
    }

}


