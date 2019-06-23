package com.mvvm.kanban_board.data.apiService

import com.mvvm.kanban_board.data.apiService.request.BoardRequest
import com.mvvm.kanban_board.data.apiService.response.TokenResponse
import com.mvvm.kanban_board.data.apiService.request.UserRequest
import com.mvvm.kanban_board.data.apiService.response.BoardResponse
import com.mvvm.kanban_board.data.apiService.response.UserRegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    //New support suspend modifier in Retrofit 2.6
    //Currently this integration only supports non-null response body types
    @POST("/api/users")
    suspend fun postUserAsync(@Body user: UserRequest): Response<UserRegisterResponse>

    @POST("/api/obtain-token/")
    suspend fun getLoginTokenAsync(@Body user: UserRequest): Response<TokenResponse>

    @POST("/api/boards/")
    suspend fun postBoardAsync(@Body board: BoardRequest): Response<BoardResponse>

    @GET("/api/boards/")
    suspend fun getAllBoardsAsync(): Response<List<BoardResponse>>

    @POST("/api/verify-token/")
    suspend fun getRefreshTokenAsync(@Body token: TokenResponse): Response<TokenResponse>




    //need authentication
    @GET("/api/users")
    suspend fun getAllUsersAsync():Response <List<UserRegisterResponse>>

}