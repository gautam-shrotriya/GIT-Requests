package com.example.gitrequests.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LandingViewModel : ViewModel() {
    private val _username = MutableLiveData<String>()
    val username: LiveData<String>
        get() = _username

    init {
        _username.value = ""
    }

    fun setUsername(username: String) {
        _username.value = username
    }

    fun isValid(username: String): Boolean {
        return username.isNotEmpty()
    }
}