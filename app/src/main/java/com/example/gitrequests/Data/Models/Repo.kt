package com.example.gitrequests.Data.Models

data class Repo(
    val name: String,
    val owner: Owner,
    val stargazersCount: Int,
    val watchersCount: Int
)
