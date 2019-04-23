package com.mvvm.kanban_board.data.db.entity

import androidx.room.PrimaryKey

data class Card(
	val name: String? = null,
	val description: String? = null,
	val owners: List<User?>? = null,
	val deadline: String? = null,
	@PrimaryKey(autoGenerate = true)
	val idC: Int? = null
)
