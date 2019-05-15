package com.mvvm.kanban_board.data.db.entity

import androidx.room.Embedded


data class UsersResponse(
	val users: List<User?>? = null
)
