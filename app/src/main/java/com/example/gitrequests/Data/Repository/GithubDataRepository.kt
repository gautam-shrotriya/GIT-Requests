package com.example.gitrequests.Data.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gitrequests.Data.Models.GitHubUser
import com.example.gitrequests.Data.Models.PrModel
import com.example.gitrequests.Data.Models.Repo
import com.example.gitrequests.Data.Services.APICallback
import com.example.gitrequests.Data.Services.APIService
import retrofit2.HttpException

class GithubDataRepository {

    var api: APICallback = APIService.retrofit.create(APICallback::class.java)
    private val TAG = "ERROR"

    private val userLiveData = MutableLiveData<GitHubUser>()
    val user: LiveData<GitHubUser>
    get() = userLiveData

    private val _userExists = MutableLiveData<Boolean>()
    val userExists: LiveData<Boolean>
        get() = _userExists

    private val _repoList = MutableLiveData<List<Repo>>()
    val repoList : LiveData<List<Repo>>
    get() = _repoList

    private val _prList = MutableLiveData<List<PrModel>>()
    val prList : LiveData<List<PrModel>>
        get() = _prList



    suspend fun getUser(username: String) {
        try {
            val result = api.getUserFromUsername(username)
            if (result.isSuccessful) {
                userLiveData.postValue(result.body())
                _userExists.postValue(true)
            } else {
                if (result.code() == 404) {
                    _userExists.postValue(false)
                }
            }
        } catch (e: HttpException) {
            _userExists.postValue(false)
        }
    }

    suspend fun getPublicRepos(username: String) {
        try{
            val result = api.getPublicRepos(username)
            if(result.body() != null) {
                _repoList.postValue(result.body())
            }
        } catch (e: HttpException) {
            Log.e(TAG, e.printStackTrace().toString())
        }

    }

    suspend fun getClosedPRs(username: String, repoName: String) {
        try{
            val result = api.getClosedPRforRepo(username, repoName)
            if(result.body() != null) {
                _prList.postValue(result.body())
            }
        } catch (e: HttpException) {
            Log.e(TAG, e.printStackTrace().toString())
        }
    }

}