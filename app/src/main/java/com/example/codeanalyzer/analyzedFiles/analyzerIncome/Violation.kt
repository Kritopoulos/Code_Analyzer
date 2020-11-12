package com.example.codeanalyzer.analyzedFiles.analyzerIncome

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Violation {


    @SerializedName("priority")
    @Expose
    private var priority: String

    @SerializedName("description")
    @Expose
    private var description: String

    @SerializedName("begin_line")
    @Expose
    private var begin_line: String

    @SerializedName("end_line")
    @Expose
    private var end_line: String

    @SerializedName("begin_column")
    @Expose
    private var begin_column: String

    @SerializedName("end_column")
    @Expose
    private var end_column: String

    @SerializedName("rule")
    @Expose
    private var rule: String

    @SerializedName("rule_set")
    @Expose
    private var rule_set: String

    @SerializedName("eClass")
    @Expose
    private var eClass: String

    @SerializedName("method")
    @Expose
    private var method: String

    constructor(
        priority: String,
        description: String,
        begin_line: String,
        end_line: String,
        begin_column: String,
        end_column: String,
        rule: String,
        rule_set: String,
        eClass: String,
        method: String
    ) {
        this.priority = priority
        this.description = description
        this.begin_line = begin_line
        this.end_line = end_line
        this.begin_column = begin_column
        this.end_column = end_column
        this.rule = rule
        this.rule_set = rule_set
        this.eClass = eClass
        this.method = method
    }


    fun getPriority():String{
        return this.priority
    }
    fun setPriority(priority : String){
        this.priority = priority
    }
    fun getDescription():String{
        return this.description
    }
    fun setDescription(description : String){
        this.description = description
    }
    fun getBeginLine():String{
        return this.begin_line
    }
    fun setBeginLine(begin_line : String){
        this.begin_line = begin_line
    }
    fun getEndLine():String{
        return this.end_line
    }
    fun setEndLine(end_line : String){
        this.end_line = end_line
    }
    fun getBeginColumn():String{
        return this.begin_column
    }
    fun setBeginColumn(begin_column : String){
        this.begin_column = begin_column
    }
    fun getEndColumn():String{
        return this.end_column
    }
    fun setEndColumn(end_column : String){
        this.end_column = end_column
    }
    fun getRule():String{
        return this.rule
    }
    fun setRule(rule:String){
        this.rule = rule
    }
    fun getRuleSet():String{
        return this.rule_set
    }
    fun setRuleSet(rule_set:String){
        this.rule_set = rule_set
    }
    fun getEClass():String{
        return this.eClass
    }
    fun setEClass(eClass:String){
        this.eClass = eClass
    }
    fun getMethod():String{
        return this.method
    }
    fun setMethod(method:String){
        this.method = method
    }

}