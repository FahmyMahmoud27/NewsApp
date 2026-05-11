package com.route.news.api.model

import com.google.gson.annotations.SerializedName

data class SourcesResponse(

    @field:SerializedName("sources")
    val sources: List<SourceDM>? = null,

    @field:SerializedName("status")
    val status: String? = null
)