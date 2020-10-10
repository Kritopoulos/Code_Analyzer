package com.example.codeanalyzer.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Branches {
    @SerializedName("name")
    @Expose
    private var userName: String


    constructor(userName: String) {
        this.userName = userName

    }

    fun getBranchName(): String {
        return userName
    }

    fun setName(userName: String) {
        this.userName = userName
    }
}