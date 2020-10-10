package com.example.codeanalyzer.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GitRepositories {

    @SerializedName("name")
    @Expose
    private var name: String

    private var securtity: Boolean
    private var owner: Owner

    constructor(id: String, full_name: Boolean, owner: Owner) {
        this.name = id
        this.securtity = full_name
        this.owner = owner
    }

    fun getName(): String {
        return name
    }

    fun getSecurity(): Boolean {
        return securtity
    }

    fun getOwner(): Owner {
        return owner
    }

    fun setName(login: String) {
        this.name = login
    }

    fun setSecurity(avatar_url: Boolean) {
        this.securtity = avatar_url
    }

    fun setOwner(owner: Owner) {
        this.owner = owner
    }
}