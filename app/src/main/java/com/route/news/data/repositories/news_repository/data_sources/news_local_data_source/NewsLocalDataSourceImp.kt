package com.route.news.data.repositories.news_repository.data_sources.news_local_data_source

import com.route.news.data.api.model.SourceDM
import com.route.news.data.api.model.SourcesResponse
import com.route.news.data.database.dao.SourcesDao
import com.route.newsc43.data.database.MyDatabase
import javax.inject.Inject

class NewsLocalDataSourceImp @Inject constructor (var dao: SourcesDao):NewsLocalDataSource {


    suspend override fun getSources(category: String): List<SourceDM> {
        return dao.getSources(category.lowercase())
    }

    override suspend fun getAllSources(): List<SourceDM> {
        return dao.getAllSources()
    }

    suspend override fun saveSources(category: String, sources: List<SourceDM>){
        return dao.saveSources(sources)
    }
}