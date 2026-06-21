package com.route.news.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    companion object {

        const val API_KEY = "9dd731412cec4102bd6ffa1a225d482a"
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getWebServices(): WebServices = retrofit.create(WebServices::class.java)

    }
}