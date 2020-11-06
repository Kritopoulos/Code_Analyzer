package com.example.codeanalyzer.analyzedFiles

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codeanalyzer.R
import com.example.codeanalyzer.analyzerIncome.QualityResponse
import com.example.codeanalyzer.apiAnalyzer.AnalyzeApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AnalyzedFilesViewModel(private var context: Context, private var url: String) :
    ViewModel() {

    private var aurl: String = url
    private lateinit var service: AnalyzeApiService
    private var retrofit: Retrofit? = null

    private val _qualityResponse = MutableLiveData<QualityResponse>()
    val qualityResponse: LiveData<QualityResponse>
        get() = _qualityResponse

    init {
        retrofitResponse()
    }

    private fun retrofitResponse() {
        try {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(context.getString(R.string.ANALYZER_BASE_URL))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
        } catch (e: Exception) {
            Log.i("autolog", "Exception")
        }
        retrofit?.let {
                service = retrofit!!.create(AnalyzeApiService::class.java)
            qualityResponse()
        }

    }

    private fun qualityResponse() {

        val callAnalyzer: Call<QualityResponse> = service.getQuality(aurl)
        callAnalyzer.enqueue(object : Callback<QualityResponse> {
            override fun onResponse(
                call: Call<QualityResponse>,
                response: Response<QualityResponse>
            ) {
                _qualityResponse.value = response.body()
                Log.d("kappa", _qualityResponse.value?.getResults()?.getOverallQuality() ?: "op")
            }

            override fun onFailure(call: Call<QualityResponse>, t: Throwable) {
                Log.d("kappa", "failed")
            }
        })
    }
}