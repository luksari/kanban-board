package com.mvvm.kanban_board.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm.kanban_board.data.apiService.response.BoardResponse
import com.mvvm.kanban_board.data.db.KanbanDao
import com.mvvm.kanban_board.data.db.entity.Board
import com.mvvm.kanban_board.data.db.entity.BoardsResponse
import com.mvvm.kanban_board.data.networkDataSource.BoardNetworkDataSource
import com.mvvm.kanban_board.data.networkDataSource.BoardNetworkDataSourceImpl
import com.mvvm.kanban_board.data.networkDataSource.UserNetworkDataSource
import com.mvvm.kanban_board.data.networkDataSource.UserNetworkDataSourceImpl
import com.mvvm.kanban_board.session.AuthenticationState
import com.mvvm.kanban_board.session.SessionManager
import kotlinx.coroutines.delay


class RepositoryImpl(
    private val kanbanDao: KanbanDao,
    private val userNetworkDataSource: UserNetworkDataSource,
    private val boardNetworkDataSource: BoardNetworkDataSource) : Repository {


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
        //always refreshing boards when entering another
        boardNetworkDataSource.loadBoards()?.let {
            val searched = it.firstOrNull{b  -> b.identifier == identifier}
            if(searched == null){
                return "Board with this indetifier does not exist!"
            } else {
                _currentBoard.value = searched
                return ""
            }
        }
        return "Server problem occured, check the internet connection"  //need handle the internet connection
    }

   init{
       userNetworkDataSource.authenticationState.observeForever{
           _authenticationState.value = it
       }
   }
}