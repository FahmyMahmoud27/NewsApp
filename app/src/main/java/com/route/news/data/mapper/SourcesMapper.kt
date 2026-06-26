package com.route.news.data.mapper

import com.route.news.data.api.model.SourceDM
import com.route.news.domain.model.Source
import javax.inject.Inject

class SourcesMapper @Inject constructor(){

    fun toSource(sourceDM: SourceDM): Source {
        return Source(
            name = sourceDM.name,
            id = sourceDM.id
        )
    }

    fun toSources(sources: List<SourceDM>): List<Source> {
        return sources.map { sourceDM ->
            return@map Source(
                name = sourceDM.name,
                id = sourceDM.id
            )
        }
    }
}