package com.example.codeanalyzer.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserDetails {

    @SerializedName("login")
    @Expose
    private var userName: String

    @SerializedName("avatar_url")
    @Expose
    private var avatarUrl: String

    @SerializedName("html_url")
    @Expose
    private var htmlUrl: String

    constructor(userName: String, avatarUrl: String, htmlUrl: String) {
        this.userName = userName
        this.avatarUrl = avatarUrl
        this.htmlUrl = htmlUrl
    }

    fun getUserName(): String {
        return userName
    }

    fun setName(userName: String) {
        this.userName = userName
    }

    fun getAvatarUrl(): String {
        return avatarUrl
    }

    fun setHtmlUrl(avatarUrl: String) {
        this.avatarUrl = avatarUrl
    }

    fun gethtmlUrl(): String {
        return htmlUrl
    }

    fun sethtmlUrl(htmlUrl: String) {
        this.htmlUrl = htmlUrl
    }
}