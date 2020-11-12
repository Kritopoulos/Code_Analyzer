package com.example.codeanalyzer.userRepositories.repositoriesBranches

import android.content.Context
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codeanalyzer.R
import com.example.codeanalyzer.services.ApiService
import com.example.codeanalyzer.databinding.UserFragmentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetBranches(username: String, repository: String, context: Context, binding : UserFragmentBinding, navController: NavController) {


    private var retrofit: Retrofit? = null
    private var service: ApiService? = null
    lateinit var branchList: List<Branches>

    init {
        val branchRecyclerView = binding.recyclerBranches
        val branchLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        branchRecyclerView.layoutManager = branchLayoutManager

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

        retrofit?.let{
            service = retrofit!!.create(ApiService::class.java)
            val branchCall: Call<List<Branches>> = service!!.getBranches(username, repository)
            branchCall.enqueue(object : Callback<List<Branches>> {
                override fun onResponse(
                    call: Call<List<Branches>>, response: Response<List<Branches>>
                ) {
                    branchList = response.body()!!
                    binding.branchesText.text = context.getString(R.string.BranchText)
                    binding.branchesText.visibility = View.VISIBLE

                    //http://localhost:8080/code_analyze?url=https://api.github.com/repos/${username}/${repository}/zipball/master
                    //val url = context.getString(R.string.ANALYZER_BASE_URL)+ "code_analyze?url=https://api.github.com/repos/${username}/${repository}/zipball/"
                    val url =  "https://api.github.com/repos/${username}/${repository}/zipball/"
                    branchRecyclerView.adapter = BranchesViewAdapter((context), branchList,navController,url)
                    binding.loadingBranches.visibility = View.GONE
                }
                override fun onFailure(call: Call<List<Branches>>, t: Throwable) {
                    branchList = emptyList()
                }
            })
        }
    }
}