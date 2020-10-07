package com.example.codeanalyzer.api

class GitRepositories {


    private var name: String
    private var full_name: String
    private var html_url: String

    constructor(id: String, full_name: String, private: String) {
        this.name = id
        this.full_name = full_name
        this.html_url = private
    }

    fun getName(): String {
        return name
    }

    fun getFullName(): String {
        return full_name
    }

    fun getHtmlUrl(): String {
        return html_url
    }

    fun setName(login: String) {
        this.name = login
    }

    fun setFullName(avatar_url: String) {
        this.full_name = avatar_url
    }

    fun setHtmlUrl(html_url: String) {
        this.html_url = html_url
    }
}