package com.mvvm.kanban_board.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mvvm.kanban_board.data.db.Converte.Converters


@Entity(tableName = "boards")
data class Board(

	@TypeConverters(Converters:: class)
	val members: List<User>,
	val name: String,
	val history: List<History>,
	val categories: List<Category>,
    @PrimaryKey(autoGenerate = true)
	val idB: Int
)
