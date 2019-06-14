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
    private val _requestMessage: MutableLiveData<String> = MutableLiveData()
    val requestMessage: LiveData<String>
        get() = _requestMessage

    private val _loaderVisibility: MutableLiveData<Int> = MutableLiveData()
    val loaderVisibility: LiveData<Int>
        get() = _loaderVisibility


    fun loginUser() {
        viewModelScope.launch {
            Log.d("LOGIN", username.value + " " + password.value)
            _loaderVisibility.value = View.VISIBLE
            repository.loginUser(username.value!!, password.value!!)
            //get messages or observe
            _loaderVisibility.value = View.GONE
        }
    }

    init {
        _requestMessage.value = ""
        _loaderVisibility.value = View.GONE


        repository.authenticationState.observeForever{
            when(it){
                AuthenticationState.INVALID_AUTHENTICATION -> _requestMessage.postValue("Invalid creditionals")
                AuthenticationState.UNAUTHENTICATED -> _requestMessage.postValue("An error occurred, check the internet connections")
            }
            _authenticationState.postValue(it)
        }
    }
}
