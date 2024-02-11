package com.example.gitrequests.Data.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gitrequests.Data.Models.GitHubUser
import com.example.gitrequests.Data.Services.APICallback
import com.example.gitrequests.Data.Services.APIService
import retrofit2.HttpException

class GithubDataRepository {

    var api: APICallback = APIService.retrofit.create(APICallback::class.java)

    private val userLiveData = MutableLiveData<GitHubUser>()
    val user: LiveData<GitHubUser>
    get() = userLiveData

    private val _userExists = MutableLiveData<Boolean>()
    val userExists: LiveData<Boolean>
        get() = _userExists

    suspend fun getUser(username: String) {
//        val result = api.getUserFromUsername(username)
//        if(result.body() != null) {
//            userLiveData.postValue(result.body())
//        }

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

}