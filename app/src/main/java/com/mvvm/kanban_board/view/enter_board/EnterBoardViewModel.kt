package com.mvvm.kanban_board.view.enter_board

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.mvvm.kanban_board.data.repo.Repository
import kotlinx.coroutines.launch

class EnterBoardViewModel(private val repository: Repository)  : ViewModel() {

    val boardCode: MutableLiveData<String> = MutableLiveData("")

    private val _loaderVisibility: MutableLiveData<Int> = MutableLiveData(View.GONE)
    val loaderVisibility: LiveData<Int>
        get() = _loaderVisibility

    private val _responseMessage: MutableLiveData<String> = MutableLiveData()
    val responseMessage: LiveData<String>
        get() = _responseMessage

    fun enterBoard() {
        viewModelScope.launch {
            _loaderVisibility.value = View.VISIBLE
            _responseMessage.value = repository.enterBoard(boardCode.value!!)
            _loaderVisibility.value = View.GONE
        }
    }


}
