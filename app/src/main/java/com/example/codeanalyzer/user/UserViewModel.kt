package com.example.codeanalyzer.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codeanalyzer.api.ApiService
import com.example.codeanalyzer.api.GitRepositories
import com.example.codeanalyzer.api.UserDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserViewModel : ViewModel() {
    private val _gitUser = MutableLiveData<UserDetails>()
    val gitUser: LiveData<UserDetails>
        get() = _gitUser

    private val _reposList = MutableLiveData<List<GitRepositories>>()
    val reposList: LiveData<List<GitRepositories>>
        get() = _reposList

    // The current score
    private val _link = MutableLiveData<String>()
    val link: LiveData<String>
        get() = _link


    init {
        try {
            val url = "https://api.github.com"
            var retrofit: Retrofit? = null
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            val service: ApiService = retrofit!!.create(ApiService::class.java)

            val callUser: Call<UserDetails> = service.getUser()
            callUser?.enqueue(object : Callback<UserDetails> {
                override fun onResponse(call: Call<UserDetails>, response: Response<UserDetails>) {
                    _gitUser.value = response.body()
                }

                override fun onFailure(call: Call<UserDetails>, t: Throwable) {
                }
            })

            val call: Call<List<GitRepositories>> = service.getRepositories()
            call?.enqueue(object : Callback<List<GitRepositories>> {
                override fun onResponse(
                    call: Call<List<GitRepositories>>, response: Response<List<GitRepositories>>
                ) {
                    _reposList.value = response.body()
                }

                override fun onFailure(call: Call<List<GitRepositories>>, t: Throwable) {
                }
            })
        } catch (e: Exception) {
            Log.i("autolog", "Exception")
        }
    }
}
