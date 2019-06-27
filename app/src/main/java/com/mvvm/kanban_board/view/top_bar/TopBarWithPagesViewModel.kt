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
       // viewModel.enteredBoard.
    }


    //need to know position?
    fun refresh(){

    }

}
