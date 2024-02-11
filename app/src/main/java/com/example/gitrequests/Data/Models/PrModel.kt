package com.example.gitrequests.Data.Models

import com.google.gson.annotations.SerializedName

data class PrModel(
    @SerializedName("title") var title: String?,
    @SerializedName("created_at") var createdDate: String?,
    @SerializedName("closed_at") var closedDate: String?,
    @SerializedName("number") var pullRequestId: Int,
    @SerializedName("user") var owner: Owner
)
