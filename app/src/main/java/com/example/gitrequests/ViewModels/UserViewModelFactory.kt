package com.example.gitrequests.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gitrequests.Data.Repository.GithubDataRepository

class UserViewModelFactory(private val repository: GithubDataRepository, private val username: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(repository, username) as T
    }
}