package com.mvvm.kanban_board.data.db.entity

data class Card(
	val name: String? = null,
	val description: String? = null,
	val owners: List<User?>? = null,
	val deadline: String? = null,
	val idC: Int? = null
)
