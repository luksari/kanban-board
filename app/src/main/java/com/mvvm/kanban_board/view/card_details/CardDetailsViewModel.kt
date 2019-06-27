package com.mvvm.kanban_board.view.card_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.mvvm.kanban_board.R
import com.mvvm.kanban_board.data.apiService.response.PageResponse
import com.mvvm.kanban_board.data.apiService.response.TaskResponse
import com.mvvm.kanban_board.data.repo.Repository
import com.mvvm.kanban_board.session.AuthenticationState
import kotlinx.coroutines.launch

class CardDetailsViewModel(private val repository: Repository)  : ViewModel() {


    private var _currentTask: TaskResponse? = null
    private var _currentPage: PageResponse? = null

    val name: MutableLiveData<String> = MutableLiveData()
    val description: MutableLiveData<String> = MutableLiveData()
    val checkedPageName: MutableLiveData<String> = MutableLiveData()


    //possible radio buttons for selected page
    val isToDoChecked: MutableLiveData<Boolean?> = MutableLiveData()
    val isInProgressChecked: MutableLiveData<Boolean?> = MutableLiveData()
    val isTestingChecked: MutableLiveData<Boolean?> = MutableLiveData()
    val isDoneChecked: MutableLiveData<Boolean?> = MutableLiveData()


    private val _author = MutableLiveData<String>()
    val author: LiveData<String>
        get() = _author

    init {
        repository.currentTask.observeForever {
            _currentTask = it
            setCurrentTaskDetails()
            checkedPageName.value = repository.currentBoardPages.value?.firstOrNull { p -> p.id == _currentTask?.page }?.name
        }

        // problem with binding and evertyhing xd,  initial this solution working..
        when (checkedPageName.value) {
            "To do" -> isToDoChecked.value = true
            "In Progress" -> isInProgressChecked.value = true
            "Testing" -> isTestingChecked.value = true
            "Done" -> isDoneChecked.value = true
            else -> {
            }
        }

        isInProgressChecked.observeForever {
            it?.let {
                if (it) checkedPageName.value = "In Progress"
            }
        }
        isToDoChecked.observeForever {
            it?.let {
                if (it) checkedPageName.value = "To do"
            }
        }
        isTestingChecked.observeForever {
            it?.let {
                if (it) checkedPageName.value = "Testing"
            }
        }
        isDoneChecked.observeForever {
            it?.let {
                if (it) checkedPageName.value = "Done"
            }
        }
    }


    private fun setCurrentTaskDetails() {
        name.value = _currentTask?.name
        description.value = _currentTask?.description
        viewModelScope.launch {
            //great api
            _author.value = repository.loadUser(_currentTask?.user)?.username
        }
    }


    fun deleteTask() {
        viewModelScope.launch {
            //first check if was no changes
            repository.deleteSelectedTask()
        }
    }

    fun editTask() {
        viewModelScope.launch {
            //update without changing if was changes, too complicated for now with page change
            val editedTask = _currentTask
            editedTask?.let {
                it.name = name.value!!
                it.description = description.value!!
                val checkedPageID =
                    repository.currentBoardPages.value?.firstOrNull { p -> p.name == checkedPageName.value }?.id
                if (checkedPageID != null) {
                    it.page = checkedPageID
                }
                repository.editTask(it)
            }
        }
    }
}
//            if(_currentTask?.name!= name.value  || _currentTask?.description != description.value ){
//
//                val editedTask = _currentTask
//                editedTask?.let{
//                    it.name = name.value!!
//                    it.description = description.value!!
//                    repository.editTask(it
//
//                }

//                //check if the page of task was edited by user, if yes, changing page id
//                if(checkedPageName.value != _currentPage?.name){
//                   val checkedPageID = repository.currentBoardPages.value?.first{p -> p.name == checkedPageName.value}?.id
//                    editedTask?.let{
//                        if (checkedPageID != null) {
//                            it.page = checkedPageID
//                        }
//                    }
//                }



