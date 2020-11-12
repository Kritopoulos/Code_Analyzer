package com.example.codeanalyzer.analyzerIncome

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Results {


    @SerializedName("analyzed_files")
    @Expose
    private var analyzed_files: Int

    @SerializedName("violations_found")
    @Expose
    private var violations_found: Int

    @SerializedName("important")
    @Expose
    private var important: Int

    @SerializedName("ordinary")
    @Expose
    private var ordinary: Int

    @SerializedName("insignificant")
    @Expose
    private var insignificant: Int

    @SerializedName("overall_Score")
    @Expose
    private var overall_Score: Double

    @SerializedName("overall_quality")
    @Expose
    private var overall_quality: String

    @SerializedName("analyzed_files_name")
    @Expose
    private var analyzed_files_name: List<String>

    @SerializedName("important_violations")
    @Expose
    private var important_violations: List<Violation>

    @SerializedName("ordinary_violations")
    @Expose
    private var ordinary_violations: List<Violation>

    @SerializedName("insignificant_violations")
    @Expose
    private var insignificant_violations: List<Violation>

    constructor(
        analyzed_files: Int,
        violations_found: Int,
        important: Int,
        ordinary: Int,
        insignificant: Int,
        overall_Score: Double,
        overall_quality: String,
        analyzed_files_name: List<String>,
        important_violations: List<Violation>,
        ordinary_violations: List<Violation>,
        insignificant_violations: List<Violation>
    ) {
        this.analyzed_files = analyzed_files
        this.violations_found = violations_found
        this.important = important
        this.ordinary = ordinary
        this.insignificant = insignificant
        this.overall_Score = overall_Score
        this.overall_quality = overall_quality
        this.analyzed_files_name = analyzed_files_name
        this.important_violations = important_violations
        this.ordinary_violations = ordinary_violations
        this.insignificant_violations = insignificant_violations
    }


    fun getAnalyzedFiles():Int {
        return this.analyzed_files
    }
    fun getViolations():Int{
        return this.violations_found
    }
    fun getImportant():Int{
        return this.important
    }
    fun getOrdinary():Int{
        return this.ordinary
    }
    fun getInsignificant():Int{
        return this.insignificant
    }
    fun getOverallScore():Double{
        return this.overall_Score
    }
    fun getOverallQuality():String{
        return this.overall_quality
    }
    fun getAnalyzedFilesName():List<String>{
        return this.analyzed_files_name
    }
    fun getImportantViolationsy():List<Violation>{
        return this.important_violations
    }
    fun getOrdinaryViolations():List<Violation>{
        return this.ordinary_violations
    }
    fun getInsignificantViolations():List<Violation>{
        return this.insignificant_violations
    }
}