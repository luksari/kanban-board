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
import com.mvvm.kanban_board.session.AuthenticationState
import org.json.JSONObject

class BoardNetworkDataSourceImpl(
    private val apiUtils: ApiUtils,
    private val pageNetworkDataSource: PageNetworkDataSource): BoardNetworkDataSource {


    private val pagesNames = arrayOf("To do", "In Progress", "Testing", "Done")

    private val _currentBoard = MutableLiveData<BoardResponse>()
    override val currentBoard: LiveData<BoardResponse>
        get() = _currentBoard

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
                    if(addPagesToBoard(it.body()!!.id)){
                        "Your board was created successfully!"
                    }else{
                        "Sorry, server error occurred, try again"
                    }
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

    override suspend fun enterBoard(identifier: String): String? {
        loadBoards()?.let{
            val searched = it.firstOrNull{b  -> b.identifier == identifier}
            return if(searched == null){
                "Board with this indetifier does not exist!"
            } else {
                _currentBoard.postValue(searched)
                ""
            }
        }
        return "Server problem occured, check the internet connection"  //need handle the internet connection
    }

    private suspend fun addPagesToBoard(boardID: Long): Boolean{
        pagesNames.forEach {
           if(!pageNetworkDataSource.addPageToBoard(it, boardID)) return false
        }
        return true
    }


}