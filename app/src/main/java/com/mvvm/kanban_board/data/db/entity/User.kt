package com.mvvm.kanban_board.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
	@PrimaryKey(autoGenerate = true)
	val idU: Long,
	val password: String? = null,
	val name: String? = null //name need to be unique
)

