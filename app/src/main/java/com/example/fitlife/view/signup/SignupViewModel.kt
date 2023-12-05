package com.example.fitlife.view.signup

import androidx.lifecycle.ViewModel
import com.example.fitlife.data.Repository

class SignupViewModel(private val repository: Repository) : ViewModel() {
    fun register(name: String, email: String, password: String) =
        repository.register(name= name, email = email, password = password)
}