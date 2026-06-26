package com.route.news.data.repositories.news_repository

import androidx.compose.ui.res.fontResource
import com.route.news.data.api.model.SourceDM
import com.route.news.data.mapper.SourcesMapper
import com.route.news.data.repositories.news_repository.data_sources.news_local_data_source.NewsLocalDataSource
import com.route.news.data.repositories.news_repository.data_sources.news_local_data_source.NewsLocalDataSourceImp
import com.route.news.data.repositories.news_repository.data_sources.news_remote_data_source.NewsRemoteDataSource
import com.route.news.data.repositories.news_repository.data_sources.news_remote_data_source.NewsRemoteDataSourceImp
import com.route.news.domain.model.Source
import com.route.news.domain.repository.NewsRepository
import com.route.news.utils.Connectivity
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    val localDataSource: NewsLocalDataSource,
    val remoteDataSource: NewsRemoteDataSource,
    val connectivity: Connectivity,
    var sourcesMapper: SourcesMapper
) : NewsRepository {


    suspend override fun getSources(category: String): List<Source> {
        val isConnected = connectivity.isOnline()
        if (isConnected) {
            val sourcesResponse = remoteDataSource.getSources(category)
            localDataSource.saveSources(category, sourcesResponse.sources!!)
            return sourcesMapper.toSources(sourcesResponse.sources)
        } else {
            var category = localDataSource.getSources(category)
            return sourcesMapper.toSources(category)
        }
    }
}
