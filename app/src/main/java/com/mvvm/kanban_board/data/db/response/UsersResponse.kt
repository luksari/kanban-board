package com.mvvm.kanban_board.data.db.response

import com.mvvm.kanban_board.data.db.entity.User

data class UsersResponse(
    val users: List<User?>? = null
)