package com.mvvm.kanban_board.data.networkDataSource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm.kanban_board.data.apiService.ApiUtils
import com.mvvm.kanban_board.data.apiService.request.BoardRequest
import com.mvvm.kanban_board.data.apiService.request.UserRequest
import com.mvvm.kanban_board.data.apiService.response.BoardResponse
import com.mvvm.kanban_board.data.db.entity.User
import org.json.JSONObject

class BoardNetworkDataSourceImpl(private val apiUtils: ApiUtils): BoardNetworkDataSource {

//    private val _loadedBoards = MutableLiveData<List<BoardResponse>>()
//    override val loadedBoards: LiveData<List<BoardResponse>>
//        get() = _loadedBoards

    override suspend fun loadBoards(): List<BoardResponse>? {
        try{
            apiUtils.apiService.getAllBoardsAsync().let{
                if(it.isSuccessful) return it.body()
            }
        }catch(e: Exception){
            //internet problems?
        }
        return null
    }

    override suspend fun addBoard(identifier: String, name: String, ownerID: Long): String {
        //temporary request with color and listOf users -> remove color, stay with board owner/creator
        var message: String
        try {
            apiUtils.apiService.postBoardAsync(BoardRequest(identifier = identifier, name = name, color = "0", users = listOf(ownerID))).let {
                message = if (it.isSuccessful) {
                    "Your board was created successfully!"
                } else {
                    val error = JSONObject(it.errorBody()?.string())
                    if (error.has("identifier")) {
                        error.getString("identifier").removePrefix("[\"").removeSuffix("\"]")
                    } else {
                        "Sorry, server error occurred, try again"
                    }
                }
            }
        } catch (e: Exception) {
            message = "An error occurred, check the internet connection"
        }
        return message
    }

    override suspend fun enterBoard(identifier: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}