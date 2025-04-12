package com.udacity.shoestore

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> get() = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> get() = _passwordError

    fun validateInputs(): Boolean {
        var isValid = true

        // Email validation
        if (email.value.isNullOrBlank() || !Patterns.EMAIL_ADDRESS.matcher(email.value!!).matches()) {
            _emailError.value = "Enter a valid email"
            isValid = false
        } else {
            _emailError.value = null
        }

        // Password validation
        if (password.value.isNullOrBlank() || password.value!!.length < 6) {
            _passwordError.value = "Password must be at least 6 characters"
            isValid = false
        } else {
            _passwordError.value = null
        }

        return isValid
    }
}
