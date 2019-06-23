package com.mvvm.kanban_board.data.apiService.response

class BoardResponse(var id: Long, var name: String, var identifier: String, var color: String, var users: List<Long>) {
}