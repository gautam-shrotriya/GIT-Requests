package com.example.gitrequests.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.gitrequests.Data.Repository.GithubDataRepository
import androidx.lifecycle.viewModelScope
import com.example.gitrequests.Data.Models.GitHubUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserViewModel(private val repository: GithubDataRepository, private val username: String) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUser(username)
        }
    }

    val user: LiveData<GitHubUser>
    get() = repository.user

    val userExists: LiveData<Boolean>
    get() = repository.userExists
}