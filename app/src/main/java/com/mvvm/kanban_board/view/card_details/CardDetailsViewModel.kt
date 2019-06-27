package com.mvvm.kanban_board.view.card_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.mvvm.kanban_board.data.apiService.response.TaskResponse
import com.mvvm.kanban_board.data.repo.Repository
import com.mvvm.kanban_board.session.AuthenticationState
import kotlinx.coroutines.launch

class CardDetailsViewModel(private val repository: Repository)  : ViewModel() {



    private var _currentTask: TaskResponse? = null


    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    private val _description = MutableLiveData<String>()
    val description: LiveData<String>
        get() = _description

    private val _author = MutableLiveData<String>()
    val author: LiveData<String>
        get() = _author

    init{
        viewModelScope.launch {
            val task = repository.loadSelectedTask()
            _currentTask = task
            setCurrentTaskDetails()
        }
    }

    private fun setCurrentTaskDetails(){
            _name.value = _currentTask?.name
            _description.value = _currentTask?.description
            viewModelScope.launch {   //great api
                _author.value = repository.loadUser(_currentTask?.user)?.username
            }
    }


    fun deleteTask(){
        viewModelScope.launch {
            //first check if was no changes
            //repository.deleteTask()
        }
    }

    fun EditTask(){
        viewModelScope.launch {
            //first check if was no changes
            //repository.editTask()
        }
    }





}
