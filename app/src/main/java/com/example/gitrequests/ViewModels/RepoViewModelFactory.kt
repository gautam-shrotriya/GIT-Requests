package com.example.gitrequests.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gitrequests.Data.Repository.GithubDataRepository

class RepoViewModelFactory(private val repository: GithubDataRepository, private val username: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReposViewModel(repository, username) as T
    }
}