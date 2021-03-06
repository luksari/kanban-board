package com.mvvm.kanban_board.data.networkDataSource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm.kanban_board.data.apiService.ApiUtils
import com.mvvm.kanban_board.data.apiService.request.TaskRequest
import com.mvvm.kanban_board.data.apiService.response.TaskResponse
import org.json.JSONObject

class TaskNetworkDataSourceImpl(private val apiUtils: ApiUtils) : TaskNetworkDataSource {
    override suspend fun deleteTasks(taskID: Long): String? {
        var message = ""
        try{
            apiUtils.apiService.deleteTaskAsync(taskID.toString()).let {
                message = if (it.isSuccessful) {
                    "Task deleted succesfully"
                } else {
                    "Server error occurred, refresh board and try again" //task was not found
                }
            }
        }catch (e: Exception) {
            message = "An error occurred, check the internet connection"
        }
        return message
    }

    override suspend fun editTasks(t: TaskResponse): String? {
        var message: String
        try{
            val updatedTask = TaskRequest(name = t.name, user = t.user, description = t.description, page = t.page)
            apiUtils.apiService.editTaskAsync(taskID = t.id.toString(), task = updatedTask ).let{
                message = if (it.isSuccessful) {
                    "Task saved!"
                } else {
                    "Sorry, server error occurred, try again"  //task not found
                }
            }
        } catch (e: Exception) {
            message = "An error occurred, check the internet connection"
        }
        return message

    }
    override suspend fun editTasks(
        taskID: Long,
        name: String,
        ownerID: Long,
        description: String,
        pageID: Long
    ): String? {
        var message: String
        try{
            val updatedTask = TaskRequest(name = name, user = ownerID, description = description, page = pageID)
            apiUtils.apiService.editTaskAsync(taskID = taskID.toString(), task = updatedTask ).let{
                message = if (it.isSuccessful) {
                    "Task saved!"
                } else {
                    "Sorry, server error occurred, try again"  //task not found
                }
            }
        } catch (e: Exception) {
            message = "An error occurred, check the internet connection"
        }
        return message

    }

    override suspend fun addTaskToPage(name: String, ownerID: Long, description: String, pageID: Long): TaskResponse? {
        try {
            apiUtils.apiService.postTaskToPageAsync(TaskRequest(name = name, user = ownerID, description = description, page = pageID) ).let {
                if(it.isSuccessful) return it.body()
            }
        } catch (e: Exception) {
           return null
        }
        return null
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

    override suspend fun loadTask(taskID: Long): TaskResponse? {
            loadAllTasks()?.let{
                return it.first { t -> t.id == taskID }
            }
        return null
    }

    override suspend fun loadPageTasks(pageID: Long?): List<TaskResponse>? {
        loadAllTasks()?.let{
            //return
            return  it.filter { t -> t.page == pageID } }
        return null
    }


}


