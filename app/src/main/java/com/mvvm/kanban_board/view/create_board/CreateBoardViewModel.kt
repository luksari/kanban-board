package com.mvvm.kanban_board.view.create_board

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.mvvm.kanban_board.data.repo.Repository
import kotlinx.coroutines.launch

class CreateBoardViewModel(private val repository: Repository)  : ViewModel() {


    val boardCode: MutableLiveData<String> = MutableLiveData()
    val name: MutableLiveData<String> = MutableLiveData()


    private val _responseMessage: MutableLiveData<String> = MutableLiveData()
    val responseMessage: LiveData<String>
        get() = _responseMessage

    private val _isValid: MutableLiveData<Boolean> = MutableLiveData(false)
    val isValid: LiveData<Boolean>
        get() = _isValid

    private val _loaderVisibility: MutableLiveData<Int> = MutableLiveData(View.GONE)
    val loaderVisibility: LiveData<Int>
        get() = _loaderVisibility

    private val _errorName: MutableLiveData<String> = MutableLiveData()
    val errorName: LiveData<String>
        get() = _errorName
    private val _errorBoardCode: MutableLiveData<String> = MutableLiveData()
    val errorBoardCode: LiveData<String>
        get() = _errorBoardCode

    private var _isNameValid: MutableLiveData<Boolean> = MutableLiveData(false)
    private var _isBoardCodeValid: MutableLiveData<Boolean> = MutableLiveData(false)

    fun createBoard(){

           Log.d("http", "CREATE BOARD")
           viewModelScope.launch {
                _loaderVisibility.value = View.VISIBLE
                _responseMessage.value = repository.createBoard(boardCode.value!!, name.value!!, 1)
                _loaderVisibility.value = View.GONE
           }
   }

    private fun validateName(_name: String) {
        if(_name.isEmpty())  {
            _isNameValid.value = false
            _errorName.value = "Board name must not be empty!"
        } else {
            _isNameValid.value = true
            _errorName.value = null
        }
    }

    private fun validateBoardCode(_boardCode: String) {
        if(_boardCode.length <8){
            _isBoardCodeValid.value = false
            _errorBoardCode.value = "Board code must contain at least 8 characters"
        }else{
            _isBoardCodeValid.value = true
            _errorBoardCode.value = null
        }
    }

    init {
        name.observeForever { validateName(it) }
        boardCode.observeForever { validateBoardCode(it) }

        _isNameValid.observeForever { _isValid.value = (it && _isBoardCodeValid.value!!) }
        _isBoardCodeValid.observeForever { _isValid.value = (it && _isNameValid.value!!) }
    }

}
