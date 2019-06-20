package com.mvvm.kanban_board.data.networkDataSource

import com.mvvm.kanban_board.data.apiService.ApiUtils
import com.mvvm.kanban_board.data.apiService.request.BoardRequest
import com.mvvm.kanban_board.data.apiService.request.UserRequest
import com.mvvm.kanban_board.data.db.entity.User
import org.json.JSONObject

class BoardNetworkDataSourceImpl(private val apiUtils: ApiUtils): BoardNetworkDataSource {

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