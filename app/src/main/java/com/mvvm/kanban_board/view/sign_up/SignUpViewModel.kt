package com.mvvm.kanban_board.view.sign_up

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.kanban_board.data.repo.Repository
import kotlinx.coroutines.*

class SignUpViewModel(private val repository: Repository) : ViewModel() {


    val password: MutableLiveData<String> = MutableLiveData()
    val username: MutableLiveData<String> = MutableLiveData()

    private val _responseMessage: MutableLiveData<String> = MutableLiveData()
    val responseMessage: LiveData<String>
        get() = _responseMessage

    private val _errorPassword: MutableLiveData<String> = MutableLiveData()
    val errorPassword: LiveData<String>
        get() = _errorPassword
    private val _errorUsername: MutableLiveData<String> = MutableLiveData()
    val errorUsername: LiveData<String>
        get() = _errorUsername

    private var _isPasswordValid: MutableLiveData<Boolean> = MutableLiveData(false)
    private var _isUsernameValid: MutableLiveData<Boolean> = MutableLiveData(false)

    private val _isValid: MutableLiveData<Boolean> = MutableLiveData(false)
    val isValid: LiveData<Boolean>
        get() = _isValid

    private val _loaderVisibility: MutableLiveData<Int> = MutableLiveData(GONE)
    val loaderVisibility: LiveData<Int>
        get() = _loaderVisibility

    fun registerNewUser() {
        viewModelScope.launch {
            _loaderVisibility.value=VISIBLE
            _responseMessage.value = repository.registerNewUser(username.value!!, password.value!!)
            _loaderVisibility.value = GONE
        }
    }

    private fun validatePassword(_password: String) {

        val PASSWORD_REGEX_LOWERCASE = """^(.*[a-z].*)$""".toRegex()
        val PASSWORD_REGEX_UPPERCASE = """^(.*[A-Z].*)$""".toRegex()
        val PASSWORD_REGEX_NUMBER = """^(.*\d.*)$""".toRegex()
        val PASSWORD_REGEX_SPEC_CHAR = """^(.*[@$!%*?&.].*)$""".toRegex()
        val PASSWORD_REGEX = """^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&.])[A-Za-z\d@$!%*?&.]{8,}$""".toRegex()
        val validate = PASSWORD_REGEX.matches(_password)

        if (validate) {
            _isPasswordValid.value = true
            _errorPassword.value = null
        } else {
            _isPasswordValid.value = false
            _errorPassword.let {
                if (_password.length < 8) {
                    it.value = "Password must be at least 8 characters long"
                } else if (!PASSWORD_REGEX_LOWERCASE.matches(_password)) {
                    it.value = "Password must contain at least one lowercase"
                } else if (!PASSWORD_REGEX_UPPERCASE.matches(_password)) {
                    it.value = "Password must contain at least one uppercase"
                } else if (!PASSWORD_REGEX_NUMBER.matches(_password)) {
                    it.value = "Password must contain at least one digit"
                } else if (!PASSWORD_REGEX_SPEC_CHAR.matches(_password)) {
                    it.value = "Password must contain at least one special character (@\$!%*?&.)"
                } else {
                    it.value = "Password can contain only these special characters (@\$!%*?&.)"
                }
            }
        }

    }

    private fun validateUsername(_username: String) {

        val USERNAME_REGEX = """^[a-zA-Z0-9@.\-_]{8,20}$""".toRegex()
        val USERNAME_REGEX_CHAR = """^[a-zA-Z0-9@.\-_]*$""".toRegex()
        var validate = USERNAME_REGEX.matches(_username)

        if (validate) {
            _isUsernameValid.value = true
            _errorUsername.value = null
        } else {
            _isUsernameValid.value = false
            _errorUsername.let {
                if (!USERNAME_REGEX_CHAR.matches(_username)) {
                    it.value = "Username must contain only letters, numbers or (@.+-_) characters"
                } else if (_username.length !in 8..20) {
                    it.value = "Username must contain 8-20 characters"
                }
            }
        }
    }

    init {
        username.observeForever { validateUsername(it) }
        password.observeForever { validatePassword(it) }

        _isPasswordValid.observeForever { _isValid.value = (it && _isUsernameValid.value!!) }
        _isUsernameValid.observeForever { _isValid.value = (it && _isPasswordValid.value!!) }
    }
}


