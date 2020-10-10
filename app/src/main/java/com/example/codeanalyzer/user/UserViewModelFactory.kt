package com.example.codeanalyzer.user

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserViewModelFactory(private val userName: String, private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            // TODO Construct and return the ScoreViewModel
            return UserViewModel(userName,context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}