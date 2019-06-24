package com.mvvm.kanban_board.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm.kanban_board.data.apiService.response.BoardResponse
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


    private val _authenticationState = MutableLiveData<AuthenticationState>()
    override val authenticationState: LiveData<AuthenticationState>
        get() = _authenticationState


    private val _currentBoard = MutableLiveData<BoardResponse>()
    override val currentBoard: LiveData<BoardResponse>
        get() = _currentBoard

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
        return boardNetworkDataSource.enterBoard(identifier)
    }

    override suspend fun loadBoardPages(){
        val pages = pageNetworkDataSource.loadBoardPages(_currentBoard.value!!.id)
        pages?.forEach { p ->
            Log.d("PAGES", p.name)
        }
    }

    override suspend fun addTaskToPage(name: String, ownerID: Long, description: String, pageID: Long): String? {
       return taskNetworkDataSource.addTaskToPage(name, ownerID, description, pageID)
    }

    override suspend fun loadPageTasks(pageID: Long) {
        val pageTasks = taskNetworkDataSource.loadPageTasks(pageID)
        Log.d("TASKS", "Displaying page's tasks if exist")
        pageTasks?.forEach { t ->
            Log.d("TASKS", t.id.toString() + ": "+ t.name)
        }
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