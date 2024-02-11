package com.example.gitrequests.Data.Services

import com.example.gitrequests.BuildConfig
import com.example.gitrequests.Data.Models.GitHubUser
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface APICallback {

    @GET("users/{username}")
    suspend fun getUserFromUsername(
        @Path("username") username: String): Response<GitHubUser>
}