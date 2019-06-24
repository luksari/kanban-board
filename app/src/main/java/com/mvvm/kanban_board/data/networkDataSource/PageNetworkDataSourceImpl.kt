package com.mvvm.kanban_board.data.networkDataSource

import com.mvvm.kanban_board.data.apiService.ApiUtils
import com.mvvm.kanban_board.data.apiService.request.BoardRequest
import com.mvvm.kanban_board.data.apiService.request.PageRequest
import com.mvvm.kanban_board.data.apiService.response.BoardResponse
import com.mvvm.kanban_board.data.apiService.response.PageResponse
import org.json.JSONObject
import retrofit2.Response

class PageNetworkDataSourceImpl(private val apiUtils: ApiUtils) : PageNetworkDataSource {

    override suspend fun addPageToBoard(name: String, boardID: Long): Boolean {
        try {
            apiUtils.apiService.postPageToBoardAsync(PageRequest(name = name, board = boardID)).let {
                if (it.isSuccessful)  return true }
        } catch (e: Exception) { return false}
        return false
    }

    override suspend fun loadAllPages(): List<PageResponse>? {
        try{
            apiUtils.apiService.getPagesAsync().let{
                if(it.isSuccessful) return it.body()
            }
        }catch(e: Exception){
            //internet problems?
        }
        return null
    }

    override suspend fun loadBoardPages(boardID: Long): List<PageResponse>? {
        loadAllPages()?.let{
            return it.filter { p -> p.board == boardID }}
        return null
    }

}