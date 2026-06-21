package com.route.news.data.repositories.news_repository.data_sources.news_local_data_source

import com.route.news.data.api.model.SourceDM
import com.route.news.data.api.model.SourcesResponse
import com.route.newsc43.data.database.MyDatabase

class NewsLocalDataSource {

    var dao = MyDatabase.getDataBase().getSourcesDao()
    suspend fun getSources(category: String): List<SourceDM> {
        return dao.getSources(category.lowercase())
    }

    suspend fun saveSources(category: String, sources: List<SourceDM>){
        return dao.saveSources(sources)
    }
}