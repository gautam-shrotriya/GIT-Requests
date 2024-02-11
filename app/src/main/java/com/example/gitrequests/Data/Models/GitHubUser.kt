package com.example.gitrequests.Data.Models

import com.google.gson.annotations.SerializedName

data class GitHubUser(
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("public_repos") val publicRepos: Int
)
