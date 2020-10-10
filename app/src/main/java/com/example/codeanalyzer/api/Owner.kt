package com.example.codeanalyzer.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Owner {
    @SerializedName("login")
    @Expose
    private var userName: String


    constructor(userName: String) {
        this.userName = userName

    }

    fun getUserName(): String {
        return userName
    }

    fun setName(userName: String) {
        this.userName = userName
    }

}