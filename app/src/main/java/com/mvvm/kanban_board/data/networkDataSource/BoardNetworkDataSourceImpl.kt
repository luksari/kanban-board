package com.mvvm.kanban_board.data.networkDataSource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm.kanban_board.data.apiService.ApiUtils
import com.mvvm.kanban_board.data.apiService.request.BoardRequest
import com.mvvm.kanban_board.data.apiService.request.PageRequest
import com.mvvm.kanban_board.data.apiService.request.UserRequest
import com.mvvm.kanban_board.data.apiService.response.BoardResponse
import com.mvvm.kanban_board.data.apiService.response.PageResponse
import com.mvvm.kanban_board.data.db.entity.User
import org.json.JSONObject

class BoardNetworkDataSourceImpl(private val apiUtils: ApiUtils): BoardNetworkDataSource {

    private val pagesNames = arrayOf("To do", "In Progress", "Testing", "Done")

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
                if (it.isSuccessful) {
                    message =  if(addPagesToBoard(it.body()!!.id)){
                        "Your board was created successfully!"
                    }else{
                        "Sorry, server error occurred, try again"
                    }
                } else {
                    val error = JSONObject(it.errorBody()?.string())
                    message = if (error.has("identifier")) {
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

    private suspend fun addPagesToBoard(boardID: Long): Boolean{
        pagesNames.forEach {
           if(!addPageToBoard(it, boardID)) return false
        }
        return true
    }
    override suspend fun addPageToBoard(name: String, boardID: Long): Boolean {
        try {
            apiUtils.apiService.postPageToBoardAsync(PageRequest(name = name, board = boardID)).let {
                 if (it.isSuccessful)  return true }
        } catch (e: Exception) { return false}
        return false
}

    override suspend fun loadPages(): List<PageResponse>? {
        try{
            apiUtils.apiService.getPagesAsync().let{
                if(it.isSuccessful) return it.body()
            }
        }catch(e: Exception){
            //internet problems?
        }
        return null
    }



}