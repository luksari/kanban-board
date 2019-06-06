package com.mvvm.kanban_board.view.sign_up

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.mvvm.kanban_board.data.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignUpViewModel(private val repository: Repository) : ViewModel() {
    // TODO: Implement the ViewModel


    fun checkPostUser(){ //pls dont hate this coroutine xd
        GlobalScope.launch (Dispatchers.Main){
            repository.registerNewUser( "emilka", "admin")
        }
    }

    fun registerNewUser(){
        GlobalScope.launch (Dispatchers.Main){
                // validation !!
            repository.registerNewUser(username.value!!, password.value!!)
        }
    }

     val password: MutableLiveData<String> = MutableLiveData()
     val username: MutableLiveData<String> = MutableLiveData()

}
