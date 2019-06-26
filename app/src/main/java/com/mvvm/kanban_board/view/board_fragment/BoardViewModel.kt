package com.mvvm.kanban_board.view.board_fragment

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.mvvm.kanban_board.R
import com.mvvm.kanban_board.data.apiService.response.TaskResponse
import com.mvvm.kanban_board.data.repo.Repository
import kotlinx.coroutines.launch

class BoardViewModel(private val repository: Repository) : ViewModel() {

    private var adapter : TaskListAdapter? = null

    private var currentPage: String? = null
    private val _loaderVisibility: MutableLiveData<Int> = MutableLiveData(View.GONE)
    val loaderVisibility: LiveData<Int>
        get() = _loaderVisibility

    private var _pageTasks: MutableLiveData<List<TaskResponse>> = MutableLiveData()
    val pageTasks: LiveData<List<TaskResponse>>
        get() = _pageTasks

    init {
        adapter = TaskListAdapter(R.layout.task_list_item, this)
    }

    fun setAdapter(cards: List<TaskResponse>){
        this.adapter?.setCards(cards)
        this.adapter?.notifyDataSetChanged();
    }
    fun getAdapter() = adapter

    fun loadPages(name: String?) {
        name?.let {
            currentPage = name
            //for testing loading pages
            viewModelScope.launch {
                _pageTasks.value = repository.loadPageTasks("To do")
                Log.d("TASKS", _pageTasks.value?.size.toString())
            }
        }
    }

    fun addTaskToPage(){
        viewModelScope.launch {
            //repository.addTaskToPage(databinding, shared, pagename)
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

//
//    //only to test
//    private val _currentPage: MutableLiveData<String> = MutableLiveData("ADD")
//    val currentPage: LiveData<String>
//        get() = _currentPage

}
