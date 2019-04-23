package com.mvvm.kanban_board.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "boards")
data class Board(
	val members: List<User?>? = null,
	val name: String? = null,
	val history: List<History?>? = null,
	val categories: List<Category?>? = null,
    @PrimaryKey(autoGenerate = true)
	val idB: Int? = null
)
