package com.example.codeanalyzer.services

import com.example.codeanalyzer.userRepositories.githubIncome.GitRepositories
import com.example.codeanalyzer.userRepositories.githubIncome.UserDetails
import com.example.codeanalyzer.userRepositories.repositoriesBranches.Branches
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/users/{userName}/repos")
    fun getRepositories(@Path("userName") userName: String): Call<List<GitRepositories>>


    @GET("/users/{userName}")
    fun getUser(@Path("userName") userName: String): Call<UserDetails>

    @GET("/repos/{userName}/{repositoryName}/branches")
    fun getBranches(
        @Path("userName") userName: String,
        @Path("repositoryName") repositoryName: String
    ): Call<List<Branches>>

}

