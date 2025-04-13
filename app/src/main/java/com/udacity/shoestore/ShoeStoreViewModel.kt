package com.udacity.shoestore

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeStoreViewModel : ViewModel() {

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

    private val _shoes = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoes: LiveData<MutableList<Shoe>> get() = _shoes

    init {
        mockShoes()
    }

    fun mockShoes() {
        val sampleShoes = listOf(
            Shoe("Air Max 90", 9.0, "Nike", "Classic running shoe with cushioned sole."),
        )

        _shoes.value = sampleShoes.toMutableList()
    }

    fun addShoe(shoe: Shoe) {
        _shoes.value?.add(shoe)
    }

}
