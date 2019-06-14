package com.mvvm.kanban_board.view.sign_in

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.mvvm.kanban_board.data.repo.Repository
import com.mvvm.kanban_board.session.AuthenticationState
import kotlinx.coroutines.launch

class SignInViewModel(private val repository: Repository)  : ViewModel() {


    val password: MutableLiveData<String> = MutableLiveData()
    val username: MutableLiveData<String> = MutableLiveData()

    private val _authenticationState = MutableLiveData<AuthenticationState>()
    val authenticationState: LiveData<AuthenticationState>
        get() = _authenticationState
    private val _responseMessage: MutableLiveData<String> = MutableLiveData()
    val responseMessage: LiveData<String>
        get() = _responseMessage

    private val _loaderVisibility: MutableLiveData<Int> = MutableLiveData()
    val loaderVisibility: LiveData<Int>
        get() = _loaderVisibility


    fun loginUser() {
        viewModelScope.launch {
            _loaderVisibility.value = View.VISIBLE
            _responseMessage.postValue(repository.loginUser(username.value!!, password.value!!))
            _loaderVisibility.value = View.GONE
        }
    }

    init {
        _responseMessage.value = ""
        _loaderVisibility.value = View.GONE

        repository.authenticationState.observeForever{
            _authenticationState.postValue(it) }
    }
}
