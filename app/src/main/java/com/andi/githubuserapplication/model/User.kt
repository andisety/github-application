package com.andi.githubuserapplication.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("avatar")
    @Expose
    var avatar: String,
    @SerializedName("company")
    @Expose
    val company: String,
    @SerializedName("follower")
    @Expose
    val follower: Int,
    @SerializedName("following")
    @Expose
    val following: Int,
    @SerializedName("location")
    @Expose
    val location: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("repository")
    @Expose
    val repository: Int,
    @SerializedName("username")
    @Expose
    val username: String
):Parcelable