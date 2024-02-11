package com.example.gitrequests.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitrequests.Data.Models.Repo
import com.example.gitrequests.Data.Repository.GithubDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReposViewModel(private val repository: GithubDataRepository, private val username: String) : ViewModel() {

    val repoList: LiveData<List<Repo>>
    get() = repository.repoList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPublicRepos(username)
        }
    }
}