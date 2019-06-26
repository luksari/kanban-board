package com.mvvm.kanban_board.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm.kanban_board.data.apiService.response.BoardResponse
import com.mvvm.kanban_board.data.apiService.response.PageResponse
import com.mvvm.kanban_board.data.apiService.response.TaskResponse
import com.mvvm.kanban_board.data.db.KanbanDao
import com.mvvm.kanban_board.data.db.entity.Board
import com.mvvm.kanban_board.data.db.entity.BoardsResponse
import com.mvvm.kanban_board.data.networkDataSource.*
import com.mvvm.kanban_board.session.AuthenticationState
import com.mvvm.kanban_board.session.SessionManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class RepositoryImpl(
    private val kanbanDao: KanbanDao,
    private val userNetworkDataSource: UserNetworkDataSource,
    private val boardNetworkDataSource: BoardNetworkDataSource,
    private val pageNetworkDataSource: PageNetworkDataSource,
    private val taskNetworkDataSource: TaskNetworkDataSource
  ) : Repository {


    override val selectedTaskID: MutableLiveData<Long> = MutableLiveData()


    private val _authenticationState = MutableLiveData<AuthenticationState>()
    override val authenticationState: LiveData<AuthenticationState>
        get() = _authenticationState


    private val _currentBoard = MutableLiveData<BoardResponse>()
    override val currentBoard: LiveData<BoardResponse>
        get() = _currentBoard

    private val _currentBoardPages = MutableLiveData<List<PageResponse>>()
    override val currentBoardPages: LiveData<List<PageResponse>>
        get() = _currentBoardPages


    init{
        // currentBoard.observeForever {  } -> here change in room
    }

    override suspend fun registerNewUser(name: String, password: String): String {
        return userNetworkDataSource.registerUser(name, password)
    }

    override suspend fun loginUser(name: String, password: String): String? {
        return userNetworkDataSource.loginUser(name, password)
    }

    override suspend fun createBoard(identifier: String, name: String): String? {
        return boardNetworkDataSource.addBoard(identifier, name, SessionManager.userID!!.toLong())
    }


    override suspend fun enterBoard(identifier: String): String? {
        //load board pages here! to get ids
        return boardNetworkDataSource.enterBoard(identifier)
    }

    override suspend fun loadBoardPages(){
//        val pages = pageNetworkDataSource.loadBoardPages(16)//_currentBoard.value!!.id)
//        pages?.forEach { p ->
//            Log.d("PAGES", p.name)
//        }
       _currentBoardPages.value = pageNetworkDataSource.loadBoardPages(16)
        // _currentBoard.value!!.id after enter bard
    }


    override suspend fun addTaskToPage(name: String, ownerID: Long, description: String, pageID: Long): String? {
        return taskNetworkDataSource.addTaskToPage(name, ownerID, description, pageID)
    }
    override suspend fun loadPageTasks(pageName: String): List<TaskResponse>?{
        loadBoardPages()
        val id = _currentBoardPages.value?.first { p -> p.name == pageName }?.id
        return taskNetworkDataSource.loadPageTasks(id)
    }

    override suspend fun addTaskToPage(pageName :String): String? {
        loadBoardPages()
        val id = _currentBoardPages.value?.first { p -> p.name == pageName }?.id
        return taskNetworkDataSource.addTaskToPage("", SessionManager.userID!!.toLong(), "", id!!)
    }

    //FIRSTLY CHECK IF THE TASK EXIST/IS NOT CHANGED
    override suspend fun deleteTask(taskID: Long): String? {
        return taskNetworkDataSource.deleteTasks(taskID)
    }

    override suspend fun editTask(
        taskID: Long,
        name: String,
        ownerID: Long,
        description: String,
        pageID: Long
    ): String? {
        return taskNetworkDataSource.editTasks(taskID, name, ownerID, description, pageID)
    }

    override suspend fun loadTask(taskID: Long) {
        taskNetworkDataSource.loadTask(taskID)
    }


   init {
       userNetworkDataSource.authenticationState.observeForever {
           _authenticationState.value = it
       }
       boardNetworkDataSource.currentBoard.observeForever {
           _currentBoard.value = it
       }
   }
}