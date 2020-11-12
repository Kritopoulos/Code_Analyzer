package com.example.codeanalyzer.userRepositories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserViewModelFactory(private val userName: String, private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            // TODO Construct and return the ScoreViewModel
            return UserViewModel(context,userName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}