package com.example.codeanalyzer.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/users/Kritopoulos/repos")
    fun getRepositories(): Call<List<GitRepositories>>

    @GET("/users/Kritopoulos")
    fun getUser(): Call<UserDetails>
}

