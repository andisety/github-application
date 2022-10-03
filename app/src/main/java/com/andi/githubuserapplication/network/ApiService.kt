package com.andi.githubuserapplication.network

import com.andi.githubuserapplication.model.response.SearchResponse
import com.andi.githubuserapplication.model.response.UserResponseDetail
import com.andi.githubuserapplication.model.response.UsersResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

companion object{
    const val TOKEN="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"
    const val query="users?q"
}
    @GET("users")
    @Headers("Authorization: token $TOKEN")
    fun getUsers():Call<UsersResponse>

    @GET("users/{username}")
    @Headers("Authorization: token $TOKEN")
    fun getUserDetail(
        @Path("username") username:String
    ):Call<UserResponseDetail>

    @GET("users/{username}/followers")
    @Headers("Authorization: token $TOKEN")
    fun getFollowers(
        @Path("username") username: String
    ):Call<UsersResponse>

    @GET("users/{username}/following")
    @Headers("Authorization: token $TOKEN")
    fun getFollowing(
        @Path("username") username: String
    ):Call<UsersResponse>

    @GET("search/users{username}")
    @Headers("Authorization: token $TOKEN")
    fun getUsersSearch(
        @Path("username") username : String,
        @Query("q")q:String

    ):Call<SearchResponse>
}