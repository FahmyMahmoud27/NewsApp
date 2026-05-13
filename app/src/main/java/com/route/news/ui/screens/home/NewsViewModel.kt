package com.route.news.ui.screens.home

import android.R.attr.category
import android.R.id.tabs
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.news.api.ApiManager
import com.route.news.api.model.ArticleDM
import com.route.news.api.model.ArticlesResponse
import com.route.news.api.model.SourceDM
import com.route.news.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel: ViewModel() {


    var tabs: MutableLiveData<List<SourceDM>?> = MutableLiveData(null)
    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var errorMessage: MutableLiveData<String?> = MutableLiveData(null)


    var articles: MutableLiveData<List<ArticleDM>?> = MutableLiveData(null)



    fun getSources(category: String) {
        isLoading.value = true

        ApiManager.getWebServices().getSources(category = category)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse?>, response: Response<SourcesResponse?>
                ) {
                    isLoading.value = false
                    Log.e("getSources - onResponse", "code = ${response.code()}")
                    if (response.isSuccessful) {
                        tabs.value = response.body()!!.sources
                    } else {
                        errorMessage.value = response.message()
                    }
                    Log.e("getSources - onResponse", "body = ${response.body()}")
                }

                override fun onFailure(
                    call: Call<SourcesResponse?>, t: Throwable
                ) {
                    isLoading.value = false
                    Log.e("getSources - onFailure", "code = ${t.message}")
                    errorMessage.value = t.message ?: "Something Went Wrong Please Try Again Later"
                }

            })
    }

    fun getArticles(source: String){
        ApiManager.getWebServices().getArticles(source = source)
            .enqueue(object : Callback<ArticlesResponse> {
                override fun onResponse(
                    call: Call<ArticlesResponse?>,
                    response: Response<ArticlesResponse?>
                ) {
                    isLoading.value = false
                    if (response.isSuccessful) {
                        articles.value = response.body()!!.articles
                    } else {
                        errorMessage.value = response.message()
                    }
                }

                override fun onFailure(
                    call: Call<ArticlesResponse?>,
                    t: Throwable
                ) {
                    isLoading.value = false
                    errorMessage.value = t.message ?: "Something Went Wrong Please Try Again Later"

                }

            })
    }
}