package com.example.gitrequests.Data.Services

import com.example.gitrequests.Data.Models.GitHubUser
import com.example.gitrequests.Data.Models.PrModel
import com.example.gitrequests.Data.Models.Repo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APICallback {

    @GET("users/{username}")
    suspend fun getUserFromUsername(
        @Path("username") username: String): Response<GitHubUser>

    @GET("users/{username}/repos")
    suspend fun getPublicRepos(
        @Path("username") username: String): Response<List<Repo>>

    @GET("repos/{username}/{repo}/pulls?state=closed")
    suspend fun getClosedPRforRepo(
        @Path("username") username: String, @Path("repo") repo: String): Response<List<PrModel>>
}