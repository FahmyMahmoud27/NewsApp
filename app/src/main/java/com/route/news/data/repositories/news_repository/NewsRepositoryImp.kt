package com.route.news.data.repositories.news_repository

import com.route.news.data.api.model.SourceDM
import com.route.news.data.repositories.news_repository.data_sources.news_local_data_source.NewsLocalDataSource
import com.route.news.data.repositories.news_repository.data_sources.news_local_data_source.NewsLocalDataSourceImp
import com.route.news.data.repositories.news_repository.data_sources.news_remote_data_source.NewsRemoteDataSource
import com.route.news.data.repositories.news_repository.data_sources.news_remote_data_source.NewsRemoteDataSourceImp
import com.route.news.domain.repository.NewsRepository
import com.route.news.utils.Connectivity
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    val localDataSource: NewsLocalDataSource,
    val remoteDataSource: NewsRemoteDataSource,
    val connectivity: Connectivity
) : NewsRepository {


    suspend override fun getSources(category: String): List<SourceDM> {
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
