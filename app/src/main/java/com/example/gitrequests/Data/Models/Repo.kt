package com.example.gitrequests.Data.Models

import com.google.gson.annotations.SerializedName

data class Repo(
    val name: String,
    val owner: Owner,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("watchers_count") val watchersCount: Int
)
