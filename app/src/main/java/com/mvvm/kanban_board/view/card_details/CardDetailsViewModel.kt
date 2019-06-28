package com.mvvm.kanban_board.view.card_details

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.mvvm.kanban_board.data.apiService.response.PageResponse
import com.mvvm.kanban_board.data.apiService.response.TaskResponse
import com.mvvm.kanban_board.data.repo.Repository
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

    private val _loaderVisibility: MutableLiveData<Int> = MutableLiveData(View.GONE)
    val loaderVisibility: LiveData<Int>
        get() = _loaderVisibility

    init {
        repository.currentTask.observeForever {
            _currentTask = it
            setCurrentTaskDetails()
            _currentPage =  repository.currentBoardPages.value?.firstOrNull { p -> p.id == _currentTask?.page }
            checkedPageName.value = _currentPage?.name
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
            _loaderVisibility.value = View.VISIBLE
            _author.value = repository.loadUser(_currentTask?.user)?.username
            _loaderVisibility.value = View.GONE
        }
    }


    fun deleteTask() {

        repository.currentPage
        viewModelScope.launch {
            //first check if was no changes
            _loaderVisibility.value = View.VISIBLE
            repository.deleteSelectedTask()
            _loaderVisibility.value = View.GONE

        }
    }

    fun editTask() {
        viewModelScope.launch {
            _loaderVisibility.value = View.VISIBLE
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
            _loaderVisibility.value = View.GONE

        }
    }
}




