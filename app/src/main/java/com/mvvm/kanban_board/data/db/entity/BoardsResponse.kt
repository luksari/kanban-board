package com.mvvm.kanban_board.data.db.entity

import androidx.room.Embedded


data class BoardsResponse (
    val boards: List<Board?>? = null
)