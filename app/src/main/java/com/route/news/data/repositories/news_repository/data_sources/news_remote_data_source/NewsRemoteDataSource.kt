package com.route.news.data.repositories.news_repository.data_sources.news_remote_data_source

import com.route.news.data.api.ApiManager
import com.route.news.data.api.model.SourcesResponse

class NewsRemoteDataSource {
    suspend fun getSources(category: String): SourcesResponse{
        return ApiManager.getWebServices().getSources(category = category)
    }

}