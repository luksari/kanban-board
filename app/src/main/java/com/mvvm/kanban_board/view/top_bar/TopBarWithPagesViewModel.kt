package com.mvvm.kanban_board.view.top_bar

import android.util.Log
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.mvvm.kanban_board.data.repo.Repository
import com.mvvm.kanban_board.session.SessionManager
import kotlinx.coroutines.launch

class TopBarWithPagesViewModel(private val repository: Repository)  : ViewModel() {



    fun logout(){
        repository.logout()
    }

    fun exitBoard(){
       repository.exitBoard()
    }

    fun refresh(){
        //to implement
    }

    fun getCurrentPageIndex() =
        when(repository.currentBoardPages.value?.first{p-> p.id == repository.currentTask.value?.page}?.name){
            "To do" -> 0
            "In Progress"-> 1
            "Testing" -> 2
            "Done" -> 3
            else -> 0
    }

}
