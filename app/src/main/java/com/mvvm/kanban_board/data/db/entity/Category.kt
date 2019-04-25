package com.mvvm.kanban_board.data.db.entity

import androidx.room.Embedded
import androidx.room.PrimaryKey

data class Category(
	val cards: List<Card?>? = null,
	@PrimaryKey(autoGenerate = true)
	val idCat: Long,
	val name: String? = null
)
