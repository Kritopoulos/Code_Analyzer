package com.example.codeanalyzer.services

import com.example.codeanalyzer.analyzedFiles.analyzerIncome.QualityResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AnalyzeApiService {

    //http://localhost:8080/code_analyze?url=https://api.github.com/repos/Kritopoulos/CurrentLocation/zipball/master

    @GET("/code_analyze")
    fun getQuality(@Query("url") url: String): Call<QualityResponse>

}

