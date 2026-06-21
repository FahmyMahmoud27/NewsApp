package com.route.news.data.repositories.news_repository

import com.route.news.data.api.model.SourceDM
import com.route.news.data.api.model.SourcesResponse
import com.route.news.data.repositories.news_repository.data_sources.news_local_data_source.NewsLocalDataSource
import com.route.news.data.repositories.news_repository.data_sources.news_remote_data_source.NewsRemoteDataSource
import com.route.newsc43.utils.Connectivity

class NewsRepository {
    val localDataSource = NewsLocalDataSource()
    val remoteDataSource = NewsRemoteDataSource()
    val connectivity = Connectivity()
    suspend fun getSources(category: String): List<SourceDM> {
        val isConnected = connectivity.isOnline()
        if (isConnected) {
            val sourcesResponse = remoteDataSource.getSources(category)
            localDataSource.saveSources(category, sourcesResponse.sources!!)
            return sourcesResponse.sources
        } else {
            return localDataSource.getSources(category)
        }
    }
}
