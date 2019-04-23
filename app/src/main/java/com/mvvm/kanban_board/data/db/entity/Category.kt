package com.mvvm.kanban_board.data.db.entity

data class Category(
	val cards: List<Card?>? = null,
	val idCat: Int? = null,
	val name: String? = null
)
