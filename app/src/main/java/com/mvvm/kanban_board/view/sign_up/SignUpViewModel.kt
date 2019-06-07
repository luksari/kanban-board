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

    fun registerNewUser(){

        GlobalScope.launch (Dispatchers.Main){
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
        get() = _errorUsername

    private val _isValid: MutableLiveData<Boolean> = MutableLiveData()
    val isValid: LiveData<Boolean>
        get() = _isValid

    private fun validatePassword(_password: String){

        val PASSWORD_REGEX_CHAR_NUM ="""^[A-Za-z\d@$!%*?&.]{8,}$""".toRegex() //min 8 characters
        val PASSWORD_REGEX_LOWERCASE = """^(.*[a-z].*)$""".toRegex()
        val PASSWORD_REGEX_UPPERCASE = """^(.*[A-Z].*)$""".toRegex()
        val PASSWORD_REGEX_NUMBER = """^(.*\d.*)$""".toRegex()
        val PASSWORD_REGEX_SPEC_CHAR = """^(.*[@$!%*?&.].*)$""".toRegex()

        val PASSWORD_REGEX = """^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&.])[A-Za-z\d@$!%*?&.]{8,}$""".toRegex()
        val validate = PASSWORD_REGEX.matches(_password)

        if(validate) {
            _isValid.value = true
            _errorPassword.value = null
        }else{
            _isValid.value = false
            _errorPassword.let{
                if (!PASSWORD_REGEX_CHAR_NUM.matches(_password)){
                    it.value = "Password must be at least 8 characters long"
                }
                else if (!PASSWORD_REGEX_LOWERCASE.matches(_password)){
                    it.value = "Password must contain at least one lowercase"
                }
                else if (!PASSWORD_REGEX_UPPERCASE.matches(_password)){
                    it.value = "Password must contain at least one uppercase"
                }
                else if (!PASSWORD_REGEX_NUMBER.matches(_password)){
                    it.value = "Password must contain at least one digit"
                }
                else if (!PASSWORD_REGEX_SPEC_CHAR.matches(_password)){
                    it.value = "Password must contain at least one special character (@\$!%*?&.)"
                }
            }
        }

    }
    private fun validateUsername(_username: String){

        val USERNAME_REGEX ="""^[a-zA-Z0-9@.\-_]{8,20}$""".toRegex()
        val USERNAME_REGEX_CHAR = """^[a-zA-Z0-9@.\-_]*$""".toRegex()
        var validate =  USERNAME_REGEX.matches(_username)

        if(validate) {
            _isValid.value = true
            _errorUsername.value = null
        }else {
            _isValid.value = false
            _errorUsername.let{
                if(!USERNAME_REGEX_CHAR.matches(_username)) {
                    it.value = "Username must contain only letters, numbers or (@.+-_) characters"
                } else {
                    it.value = "Username must contain 8-20 characters"
                }
            }
        }
    }

    init{
        _isValid.value = false
        username.observeForever { validateUsername(it) }
        password.observeForever { validatePassword(it) }
    }
}


