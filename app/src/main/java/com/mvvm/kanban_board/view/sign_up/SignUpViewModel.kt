package com.mvvm.kanban_board.view.sign_up

import androidx.lifecycle.ViewModel;
import com.mvvm.kanban_board.data.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignUpViewModel(private val repository: Repository) : ViewModel() {
    // TODO: Implement the ViewModel

    fun checkPostUser(){ //pls dont hate this coroutine xd
        GlobalScope.launch (Dispatchers.Main){
            repository.registerNewUser("admin", "justi2")
        }
    }
}
