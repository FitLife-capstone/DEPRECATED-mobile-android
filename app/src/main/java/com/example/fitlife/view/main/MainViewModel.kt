package com.example.fitlife.view.main

import androidx.lifecycle.*
import com.example.fitlife.data.Repository
import com.example.fitlife.data.pref.UserModel
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

//    fun getAllStories(token: String) =
//        repository.getAllStories(token)

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

}