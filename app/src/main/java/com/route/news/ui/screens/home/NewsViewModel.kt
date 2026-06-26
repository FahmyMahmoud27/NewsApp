package com.route.news.ui.screens.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.news.data.api.ApiManager
import com.route.news.data.api.model.ArticleDM
import com.route.news.data.api.model.SourceDM
import com.route.news.data.repositories.news_repository.NewsRepositoryImp
import com.route.news.data.repositories.news_repository.data_sources.news_local_data_source.NewsLocalDataSourceImp
import com.route.news.data.repositories.news_repository.data_sources.news_remote_data_source.NewsRemoteDataSourceImp
import com.route.news.domain.repository.NewsRepository
import com.route.news.domain.usecase.GetSourcesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch


// view -> viewModel -> UseCase -> Repo -> DataSource
@HiltViewModel
class NewsViewModel @Inject constructor(val getSourcesUseCase: GetSourcesUseCase) : ViewModel() {


    var tabs: MutableLiveData<List<SourceDM>?> = MutableLiveData(null)
    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var isLoadingArticles: MutableLiveData<Boolean> = MutableLiveData(false)
    var errorMessage: MutableLiveData<String?> = MutableLiveData(null)
    var articlesErrorMessage: MutableLiveData<String?> = MutableLiveData(null)
    var articles: MutableLiveData<List<ArticleDM>?> = MutableLiveData(null)
    fun getSources(category: String) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                tabs.value = getSourcesUseCase.execute(category = category)
                isLoading.value = false
            } catch (t: Throwable) {
                isLoading.value = false
                Log.e("getSources - onFailure", "code = ${t.message}")
                errorMessage.value = t.message ?: "Something Went Wrong Please Try Again Later"

            }


        }
    }//7

    //25 -> Sources Response -> Room
    fun getArticles(source: String) {
        viewModelScope.launch {
            try {
                isLoadingArticles.value = true
                var articlesResponse = ApiManager.getWebServices().getArticles(source = source)
                isLoadingArticles.value = false
                articles.value = articlesResponse.articles
            } catch (t: Throwable) {
                isLoadingArticles.value = false
                Log.e("getArticles - onFailure", "code = ${t.message}")
                articlesErrorMessage.value =
                    t.message ?: "Something Went Wrong Please Try Again Later"
            }
        }
    }
}