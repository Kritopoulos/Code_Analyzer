package com.example.codeanalyzer.analyzedFiles

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class AnalyzedFilesViewModelFactory(private val context: Context, private val url: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnalyzedFilesViewModel::class.java)) {
            // TODO Construct and return the ScoreViewModelii
            return AnalyzedFilesViewModel(context, url) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}