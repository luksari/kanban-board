package com.mvvm.kanban_board.data.apiService.request

import com.mvvm.kanban_board.data.db.entity.User

class BoardRequest(var name: String?=null, var identifier: String, var color: String?=null, var users: List<Long>?=null) {
}