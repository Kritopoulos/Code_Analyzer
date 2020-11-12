package com.example.codeanalyzer.analyzedFiles.analyzerIncome

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class QualityResponse {


    @SerializedName("Response")
    @Expose
    private var results: Results

    constructor(results: Results) {
        this.results = results
    }

    fun getResults():Results{
        return results
    }
    fun setResults(results : Results){
        this.results = results
    }
}