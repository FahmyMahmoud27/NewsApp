package com.route.news.data.api

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    companion object {

        const val API_KEY = "9dd731412cec4102bd6ffa1a225d482a"

        val okHttpClient = OkHttpClient.Builder()

            .addInterceptor { chain ->
                val request : Request = chain.request()
                    .newBuilder()
                    .addHeader("X-Api-Key", ApiManager.API_KEY)
                    .build()
                val response : Response = chain.proceed(request)
                return@addInterceptor response
            }
            .build()
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        fun getWebServices(): WebServices = retrofit.create(WebServices::class.java)

    }
}