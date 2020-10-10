package com.example.codeanalyzer.user

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codeanalyzer.R

import com.example.codeanalyzer.api.ApiService
import com.example.codeanalyzer.api.Branches
import com.example.codeanalyzer.api.GitRepositories
import com.example.codeanalyzer.api.UserDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserViewModel(userName:String,context:Context) : ViewModel() {
    private val _gitUser = MutableLiveData<UserDetails>()
    val gitUser: LiveData<UserDetails>
        get() = _gitUser
    private val _reposList = MutableLiveData<List<GitRepositories>>()
    val reposList: LiveData<List<GitRepositories>>
        get() = _reposList

    private val _branchList = MutableLiveData<List<Branches>>()
    val branchList: LiveData<List<Branches>>
        get() = _branchList

    private var username:String = userName
    private var context:Context = context

    init {
        try {
            val url =  context.getString(R.string.BASE_URL)
            var retrofit: Retrofit? = null
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            val service: ApiService = retrofit!!.create(ApiService::class.java)
            val callUser: Call<UserDetails> = service.getUser(username)
            callUser?.enqueue(object : Callback<UserDetails> {
                override fun onResponse(call: Call<UserDetails>, response: Response<UserDetails>) {
                    _gitUser.value = response.body()
                }

                override fun onFailure(call: Call<UserDetails>, t: Throwable) {
                    _gitUser.value = null
                }
            })
            val call: Call<List<GitRepositories>> = service.getRepositories(username)
            call?.enqueue(object : Callback<List<GitRepositories>> {
                override fun onResponse(
                    call: Call<List<GitRepositories>>, response: Response<List<GitRepositories>>
                ) {
                    _reposList.value = response.body()
                }
                override fun onFailure(call: Call<List<GitRepositories>>, t: Throwable) {
                    _reposList.value = null
                }
            })

            val branchCall: Call<List<Branches>> = service.getBranches()
            branchCall?.enqueue(object : Callback<List<Branches>> {
                override fun onResponse(
                    call: Call<List<Branches>>, response: Response<List<Branches>>
                ) {
                    _branchList.value = response.body()
                }
                override fun onFailure(call: Call<List<Branches>>, t: Throwable) {
                    _branchList.value = null
                }
            })
        } catch (e: Exception) {
            Log.i("autolog", "Exception")
        }
    }
}
