package com.example.codeanalyzer.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/users/{userName}/repos")
    fun getRepositories(@Path("userName") userName : String): Call<List<GitRepositories>>


    @GET("/users/{userName}")
    fun getUser(@Path("userName") userName : String): Call<UserDetails>

    @GET("/repos/Kritopoulos/Code_Analyzer/branches")
    fun getBranches():Call<List<Branches>>
}

