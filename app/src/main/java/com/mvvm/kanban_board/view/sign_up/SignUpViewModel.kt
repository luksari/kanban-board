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



    fun checkPostUser(){ //pls dont hate this coroutine xd
        GlobalScope.launch (Dispatchers.Main){
            repository.registerNewUser( "emilka", "admin")
        }
    }

    fun registerNewUser(){
        GlobalScope.launch (Dispatchers.Main){

            validatePassword(password.value!!)
            validateUsername(username.value!!)
            //if validate then...
            repository.registerNewUser(username.value!!, password.value!!)
        }
    }

     val password: MutableLiveData<String> = MutableLiveData()
     val username: MutableLiveData<String> = MutableLiveData()


    private val _errorPassword: MutableLiveData<String> = MutableLiveData()
    val errorPassword: LiveData<String>
        get() = _errorPassword
    private val _errorUsername: MutableLiveData<String> = MutableLiveData()
    val errorUsername: LiveData<String>
        get() = _errorPassword

    private fun validatePassword(_password: String){

        // The password must be at least 8 characters long and include a number,
        // lowercase letter, uppercase letter and special character (e.g. @, &amp;, #, ?)

        val PASSWORD_REGEX = """ ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$""".toRegex()
        var validate = PASSWORD_REGEX.matches(_password)

        Log.d("validation passw", validate.toString() + _password)
        if(validate)  _errorPassword.value = ""
        else  _errorPassword.value = "Password must be at least 8 characters long and include a number, " +
                "lowercase letter, uppercase letter and special character"
    }
    private fun validateUsername(_username: String){
        // The username should contain 8-20 characters, what more?

        var validate = (_username.length in 8..20)

        Log.d("validation name", validate.toString() +  _errorUsername.value)
        if(validate) _errorUsername.value = ""
        else  _errorUsername.value = "Username should contain 8-20 characters"
       // Log.d("validation", validate.toString())

    }

    init{
        //, error not working because username is null at the begining because of the focus
        username.observeForever { validateUsername(it) }
        password.observeForever { validatePassword(it) }

    }
}


