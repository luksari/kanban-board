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
import kotlinx.coroutines.delay


class RepositoryImpl(
    private val kanbanDao: KanbanDao,
    private val userNetworkDataSource: UserNetworkDataSource,
    private val boardNetworkDataSource: BoardNetworkDataSource
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
        val pages = boardNetworkDataSource.loadBoardPages(_currentBoard.value!!.id)
        pages?.forEach { p ->
            Log.d("PAGES", p.name)
        }
    }

   init{
       userNetworkDataSource.authenticationState.observeForever{
           _authenticationState.value = it
       }
       boardNetworkDataSource.currentBoard.observeForever {
           _currentBoard.value = it
       }
   }
}