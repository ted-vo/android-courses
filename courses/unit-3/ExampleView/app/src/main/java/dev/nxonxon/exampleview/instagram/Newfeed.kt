package dev.nxonxon.exampleview.instagram

import com.google.gson.annotations.SerializedName

data class Newfeed(
        @SerializedName("id")
        val id: Int,

        @SerializedName("image_url")
        val imageURL: String,

        @SerializedName("user")
        val user: User,

        @SerializedName("view_count")
        val viewCount: Int,

        @SerializedName("like")
        val like: Boolean = false,

        @SerializedName("bookmark")
        val bookMark: Boolean = false)

data class User(
        val name: String,
        val avatarURL: String)