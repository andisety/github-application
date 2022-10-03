package com.andi.githubuserapplication.model.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class SearchResponse(
    @SerializedName("incomplete_results")
    @Expose
    val incompleteResults: Boolean,
    @SerializedName("items")
    @Expose
    val items: UsersResponse,
    @SerializedName("total_count")
    @Expose
    val totalCount: Int
)