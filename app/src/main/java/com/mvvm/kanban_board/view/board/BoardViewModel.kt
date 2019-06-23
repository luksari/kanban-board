package com.mvvm.kanban_board.view.board

import android.util.Log
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.mvvm.kanban_board.data.repo.Repository
import kotlinx.coroutines.launch

class BoardViewModel(private val repository: Repository) : ViewModel() {
    // TODO: Implement the ViewModel

    init{
        //for testing loading pages
        viewModelScope.launch{
            repository.loadBoardPages()
        }

    }
}
