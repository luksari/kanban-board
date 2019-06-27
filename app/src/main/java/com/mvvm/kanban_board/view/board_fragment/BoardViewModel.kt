package com.mvvm.kanban_board.view.board_fragment

import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.mvvm.kanban_board.R
import com.mvvm.kanban_board.data.apiService.response.TaskResponse
import com.mvvm.kanban_board.data.repo.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BoardViewModel(private val repository: Repository) : ViewModel() {

    private var adapter : TaskListAdapter? = null

    private var currentPage: String? = null

    private val _loaderVisibility: MutableLiveData<Int> = MutableLiveData(View.GONE)
    val loaderVisibility: LiveData<Int>
        get() = _loaderVisibility

    private val _isTaskAdded: MutableLiveData<Boolean> = MutableLiveData(false)
    val isTaskAdded: LiveData<Boolean>
        get() = _isTaskAdded

    private var _pageTasks: MutableLiveData<List<TaskResponse>> = MutableLiveData()
    val pageTasks: LiveData<List<TaskResponse>>
        get() = _pageTasks

    init {
        adapter = TaskListAdapter(R.layout.task_list_item, this)
    }

    fun getTaskOfPosition(id: Int) = _pageTasks.value?.get(id)

    fun setAdapter(tasks: List<TaskResponse>){
        this.adapter?.setCards(tasks)
        this.adapter?.notifyDataSetChanged()
    }
    fun getAdapter() = adapter

    fun loadPages(page: String?) {
        page?.let {
            currentPage = page
            viewModelScope.launch {
                _pageTasks.value = repository.loadPageTasks(page)
            }
        }
    }

    fun selectTask(taskID: Long){
        viewModelScope.launch {
            repository.setCurrentTask(taskID)
            _selectedTask.value = repository.currentTask.value
        }
    }
    fun addTaskToPage(){
        viewModelScope.launch {
                _isTaskAdded.value = false
                _loaderVisibility.value = VISIBLE
                repository.addTaskToPage(currentPage!!)
                _isTaskAdded.value = true
                _loaderVisibility.value = GONE
        }
    }


    //need to observe selected task to display card_detail_fragment (cannot setup listeners on non existing buttons)
    private val _selectedTask: MutableLiveData<TaskResponse> = MutableLiveData()
    val selectedTask: LiveData<TaskResponse>
        get() = _selectedTask

}
