package com.mvvm.kanban_board.data.db.entity

data class Board(
	val members: List<User?>? = null,
	val name: String? = null,
	val history: List<History?>? = null,
	val categories: List<Category?>? = null,
	val idB: Int? = null
)
