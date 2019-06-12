package com.mvvm.kanban_board.data.repo

import com.mvvm.kanban_board.data.db.KanbanDao
import com.mvvm.kanban_board.data.networkDataSource.UserNetworkDataSource
import org.mindrot.jbcrypt.BCrypt


class RepositoryImpl(
    private val kanbanDao: KanbanDao,
    private val userNetworkDataSource: UserNetworkDataSource
) : Repository {
    override suspend fun registerNewUser(name: String, password: String): String? {
        var passwordHashed = hashPassword(password)
        return userNetworkDataSource.registerUser(name, passwordHashed)
    }

    private fun hashPassword(password: String): String {
        var salt = BCrypt.gensalt(5)
        return BCrypt.hashpw(password, salt)
    }


}