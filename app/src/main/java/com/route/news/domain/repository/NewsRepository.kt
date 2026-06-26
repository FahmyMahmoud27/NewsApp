package com.route.news.domain.repository

import com.route.news.data.api.model.SourceDM

interface NewsRepository {

    suspend fun getSources(category: String): List<SourceDM>

}