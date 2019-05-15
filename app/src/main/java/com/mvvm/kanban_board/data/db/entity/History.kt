package com.mvvm.kanban_board.data.db.entity

import androidx.room.PrimaryKey

data class History(
	@PrimaryKey(autoGenerate = true)
	val idH: Long,
	val content: String? = null
)
