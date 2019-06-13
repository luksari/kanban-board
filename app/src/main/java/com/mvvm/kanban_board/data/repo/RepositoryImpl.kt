package com.mvvm.kanban_board.data.repo

import com.mvvm.kanban_board.data.db.KanbanDao
import com.mvvm.kanban_board.data.networkDataSource.UserNetworkDataSource
import kotlinx.coroutines.delay
import org.mindrot.jbcrypt.BCrypt


class RepositoryImpl(
    private val kanbanDao: KanbanDao,
    private val userNetworkDataSource: UserNetworkDataSource
) : Repository {
    override suspend fun registerNewUser(name: String, password: String): String? {
        return userNetworkDataSource.registerUser(name, password)
    }
}