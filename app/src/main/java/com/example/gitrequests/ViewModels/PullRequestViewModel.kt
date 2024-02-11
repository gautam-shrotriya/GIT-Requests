package com.example.gitrequests.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitrequests.Data.Models.PrModel
import com.example.gitrequests.Data.Repository.GithubDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PullRequestViewModel(private val repository: GithubDataRepository, private val username: String, private val repoName: String) : ViewModel() {

    val prList: LiveData<List<PrModel>>
        get() = repository.prList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getClosedPRs(username, repoName)
        }
    }
}