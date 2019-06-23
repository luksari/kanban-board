package com.mvvm.kanban_board.data.networkDataSource

import com.mvvm.kanban_board.data.apiService.response.PageResponse

interface PageNetworkDataSource {

    suspend fun addPageToBoard(name: String, boardID: Long): PageResponse? //String?
    suspend fun loadBoardPages(): List<PageResponse>?
}