package com.example.codeanalyzer.userRepositories.githubIncome

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

    @SerializedName("public_repos")
    @Expose
    private var publicRepos: Int

    constructor(userName: String, avatarUrl: String, htmlUrl: String,publicRepos:Int) {
        this.userName = userName
        this.avatarUrl = avatarUrl
        this.htmlUrl = htmlUrl
        this.publicRepos = publicRepos
    }

    fun getUserName(): String {
        return userName
    }

    fun setUserName(userName: String) {
        this.userName = userName
    }

    fun getAvatarUrl(): String {
        return avatarUrl
    }

    fun setAvatarUrl(avatarUrl:String) {
        this.avatarUrl = avatarUrl
    }

    fun setHtmlUrl(avatarUrl: String) {
        this.avatarUrl = avatarUrl
    }

    fun getHtmlUrl(): String {
        return htmlUrl
    }

    fun getPublicRepos(): Int {
        return publicRepos
    }

    fun setPublicRepos(publicRepos: Int) {
        this.publicRepos = publicRepos
    }
}