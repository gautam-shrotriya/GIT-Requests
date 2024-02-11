package com.example.gitrequests.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gitrequests.Data.Repository.GithubDataRepository

class PrViewModelFactory(private val repository: GithubDataRepository, private val username: String, private val repoName: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PullRequestViewModel(repository, username, repoName) as T
    }
}