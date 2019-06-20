package com.mvvm.kanban_board.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm.kanban_board.data.db.KanbanDao
import com.mvvm.kanban_board.data.networkDataSource.BoardNetworkDataSource
import com.mvvm.kanban_board.data.networkDataSource.BoardNetworkDataSourceImpl
import com.mvvm.kanban_board.data.networkDataSource.UserNetworkDataSource
import com.mvvm.kanban_board.data.networkDataSource.UserNetworkDataSourceImpl
import com.mvvm.kanban_board.session.AuthenticationState
import kotlinx.coroutines.delay


class RepositoryImpl(
    private val kanbanDao: KanbanDao,
    private val userNetworkDataSource: UserNetworkDataSource,
    private val boardNetworkDataSource: BoardNetworkDataSource
) : Repository {
    private val _authenticationState = MutableLiveData<AuthenticationState>()
    override val authenticationState: LiveData<AuthenticationState>
        get() = _authenticationState

    override suspend fun registerNewUser(name: String, password: String): String {
        return userNetworkDataSource.registerUser(name, password)
    }

    override suspend fun loginUser(name: String, password: String): String? {
        return userNetworkDataSource.loginUser(name, password)
    }

    override suspend fun createBoard(identifier: String, name: String, ownerID: Long): String? {
        return boardNetworkDataSource.addBoard(identifier, name, ownerID)
    }

   init{
       userNetworkDataSource.authenticationState.observeForever{
           _authenticationState.value = it
       }
   }
}