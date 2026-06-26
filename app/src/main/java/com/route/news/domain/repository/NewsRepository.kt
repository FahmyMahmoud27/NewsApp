package com.route.news.domain.repository

import com.route.news.data.api.model.SourceDM
import com.route.news.domain.model.Source

interface NewsRepository {

    suspend fun getSources(category: String): List<Source>

}