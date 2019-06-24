package com.mvvm.kanban_board.data.apiService

import com.mvvm.kanban_board.data.apiService.request.BoardRequest
import com.mvvm.kanban_board.data.apiService.request.PageRequest
import com.mvvm.kanban_board.data.apiService.request.TaskRequest
import com.mvvm.kanban_board.data.apiService.request.UserRequest
import com.mvvm.kanban_board.data.apiService.response.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    //New support suspend modifier in Retrofit 2.6
    //Currently this integration only supports non-null response body types
    @POST("/api/users/")
    suspend fun postUserAsync(@Body user: UserRequest): Response<UserRegisterResponse>

    @POST("/api/obtain-token/")
    suspend fun getLoginTokenAsync(@Body user: UserRequest): Response<TokenResponse>

    @POST("/api/boards/")
    suspend fun postBoardAsync(@Body board: BoardRequest): Response<BoardResponse>

    @GET("/api/boards/")
    suspend fun getAllBoardsAsync(): Response<List<BoardResponse>>

    @GET("/api/pages/")
    suspend fun getPagesAsync(): Response<List<PageResponse>>

    @POST("/api/pages/")
    suspend fun postPageToBoardAsync(@Body page: PageRequest): Response<PageResponse>

    @GET("/api/tasks/")
    suspend fun getAllTasksAsync(): Response<List<TaskResponse>>

    @POST("/api/tasks/")
    suspend fun postTaskToPageAsync(@Body task: TaskRequest): Response<TaskResponse>

    @PUT("/api/tasks/{id}/")
    suspend fun editTaskAsync(@Path("id") taskID: String, @Body task: TaskRequest): Response<TaskResponse>

    @DELETE("/api/tasks/{id}/")
    suspend fun deleteTaskAsync(@Path("id") taskID: String): Response<TaskResponse>

    //put task, delete task




    @POST("/api/verify-token/")
    suspend fun getRefreshTokenAsync(@Body token: TokenResponse): Response<TokenResponse>




    //need authentication
    @GET("/api/users/")
    suspend fun getAllUsersAsync():Response <List<UserRegisterResponse>>

}