package com.route.news.domain.usecase

import com.route.news.data.api.model.SourceDM
import com.route.news.domain.model.Source
import com.route.news.domain.repository.NewsRepository
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(var repository : NewsRepository) {

    suspend fun execute(category: String) : List<Source>  = repository.getSources(category)

}