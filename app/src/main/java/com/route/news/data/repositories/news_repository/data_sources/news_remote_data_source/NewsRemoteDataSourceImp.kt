package com.route.news.data.repositories.news_repository.data_sources.news_remote_data_source

import com.route.news.data.api.ApiManager
import com.route.news.data.api.WebServices
import com.route.news.data.api.model.SourcesResponse
import javax.inject.Inject

class NewsRemoteDataSourceImp @Inject constructor(var services: WebServices) : NewsRemoteDataSource {
    suspend override fun getSources(category: String): SourcesResponse {
        return services.getSources(category = category)
    }

}