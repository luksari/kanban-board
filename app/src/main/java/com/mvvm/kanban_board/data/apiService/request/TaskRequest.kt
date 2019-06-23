package com.mvvm.kanban_board.data.apiService.request

class TaskRequest(var name: String, var user: Long, var description: String, var page: Long) {
}