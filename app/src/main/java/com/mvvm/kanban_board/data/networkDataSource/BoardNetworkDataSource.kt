package com.mvvm.kanban_board.data.networkDataSource

import androidx.lifecycle.LiveData
import com.mvvm.kanban_board.data.apiService.response.BoardResponse
import com.mvvm.kanban_board.data.db.entity.User
import com.mvvm.kanban_board.session.AuthenticationState

interface BoardNetworkDataSource {

    suspend fun addBoard(identifier: String, name: String, ownerID: Long): String
    suspend fun enterBoard(identifier: String) : String
    suspend fun loadBoards(): List<BoardResponse>?
//    val loadedBoards: LiveData<List<BoardResponse>>

    //suspend fun deleteBoard(identifier: String): String
    //suspend fun modifyBoard(identifier: String, color: String, username: String) : String
    //suspend fun modifyBoardMembers(members: List<User>)
}