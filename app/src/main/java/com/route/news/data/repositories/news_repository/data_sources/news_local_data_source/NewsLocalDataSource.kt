package com.route.news.data.repositories.news_repository.data_sources.news_local_data_source

import com.route.news.data.api.model.SourceDM
import com.route.news.data.api.model.SourcesResponse
import com.route.newsc43.data.database.MyDatabase

interface NewsLocalDataSource {

    suspend fun getSources(category: String): List<SourceDM>
    suspend fun getAllSources(): List<SourceDM>
    suspend fun saveSources(category: String, sources: List<SourceDM>)

}