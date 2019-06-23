package com.mvvm.kanban_board.data.networkDataSource

import androidx.lifecycle.LiveData
import com.mvvm.kanban_board.data.apiService.response.BoardResponse
import com.mvvm.kanban_board.data.apiService.response.PageResponse
import com.mvvm.kanban_board.data.db.entity.Board
import com.mvvm.kanban_board.data.db.entity.User
import com.mvvm.kanban_board.session.AuthenticationState

interface BoardNetworkDataSource {

    suspend fun addBoard(identifier: String, name: String, ownerID: Long): String
    suspend fun enterBoard(identifier: String) : String?
    suspend fun loadBoards(): List<BoardResponse>?

    suspend fun loadBoardPages(boardID: Long): List<PageResponse>?
    val currentBoard: LiveData<BoardResponse>

}