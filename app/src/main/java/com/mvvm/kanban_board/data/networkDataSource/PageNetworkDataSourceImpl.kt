package com.mvvm.kanban_board.data.networkDataSource

import com.mvvm.kanban_board.data.apiService.ApiUtils
import com.mvvm.kanban_board.data.apiService.request.BoardRequest
import com.mvvm.kanban_board.data.apiService.request.PageRequest
import com.mvvm.kanban_board.data.apiService.response.BoardResponse
import com.mvvm.kanban_board.data.apiService.response.PageResponse
import org.json.JSONObject
import retrofit2.Response

class PageNetworkDataSourceImpl(private val apiUtils: ApiUtils) : PageNetworkDataSource {

    override suspend fun addPageToBoard(name: String, boardID: Long): PageResponse? {
        //var message: String
        try {
            apiUtils.apiService.postPageToBoardAsync(PageRequest(name = name, board = boardID)).let {
                return if(it.isSuccessful) it.body()
                else null
                //message = if (it.isSuccessful) { "" }
               // else { "Sorry, server error occurred, try again" }
            }
        } catch (e: Exception) {
            return null
            //message = "An error occurred, check the internet connection"
        }
        //return message
    }

    override suspend fun loadBoardPages(): List<PageResponse>? {
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