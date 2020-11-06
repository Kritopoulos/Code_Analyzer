package com.example.codeanalyzer.user

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codeanalyzer.R
import com.example.codeanalyzer.analyzedFiles.AnalyzedFilesFragmentArgs

import com.example.codeanalyzer.apiGithub.ApiService
import com.example.codeanalyzer.apiGithub.Branches
import com.example.codeanalyzer.apiGithub.GitRepositories
import com.example.codeanalyzer.apiGithub.UserDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserViewModel(private var context: Context, userName: String) : ViewModel() {

    private val _gitUser = MutableLiveData<UserDetails>()
    val gitUser: LiveData<UserDetails>
        get() = _gitUser

    private val _reposList = MutableLiveData<List<GitRepositories>>()
    val reposList: LiveData<List<GitRepositories>>
        get() = _reposList

    private val _branchList = MutableLiveData<List<Branches>>()
    val branchList: LiveData<List<Branches>>
        get() = _branchList

    private val _apiService = MutableLiveData<ApiService>()
    val apiService: MutableLiveData<ApiService>
        get() = _apiService

    private var username: String = userName

    private lateinit var service: ApiService

    private var retrofit: Retrofit? = null

    init {
        retrofitResponse()
    }

    private fun retrofitResponse() {
        try {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(context.getString(R.string.BASE_URL))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
        } catch (e: Exception) {
            Log.i("autolog", "Exception")
        }
        retrofit?.let {
            service = retrofit!!.create(ApiService::class.java)
            _apiService.value = service
            gitUserDetails()
        }


    }

    private fun gitUserDetails() {
        val callUser: Call<UserDetails> = service.getUser(username)
        callUser.enqueue(object : Callback<UserDetails> {
            override fun onResponse(call: Call<UserDetails>, response: Response<UserDetails>) {

                _gitUser.value = response.body()
                gitRepos()

            }

            override fun onFailure(call: Call<UserDetails>, t: Throwable) {
                _gitUser.value = null
            }
        })
    }

    fun gitRepos() {
        val call: Call<List<GitRepositories>> = service.getRepositories(username)
        call.enqueue(object : Callback<List<GitRepositories>> {
            override fun onResponse(
                call: Call<List<GitRepositories>>, response: Response<List<GitRepositories>>
            ) {
                _reposList.value = response.body()
                //fun gitBranches()
            }

            override fun onFailure(call: Call<List<GitRepositories>>, t: Throwable) {
                _reposList.value = null
            }
        })
    }

//    fun gitBranches() {
//        val branchCall: Call<List<Branches>> =
//            service.getBranches(username, _reposList.value!!.get(0).getName())
//
//        branchCall.enqueue(object : Callback<List<Branches>> {
//            override fun onResponse(
//                call: Call<List<Branches>>, response: Response<List<Branches>>
//            ) {
//                _branchList.value = response.body()
//            }
//            override fun onFailure(call: Call<List<Branches>>, t: Throwable) {
//                _branchList.value = null
//            }
//        })
//    }
}
