package com.route.news.data.di

import android.util.Log
import com.route.news.data.api.ApiManager
import com.route.news.data.api.WebServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides//54
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
//        val logging = HttpLoggingInterceptor {
//            Log.e("Api", it)
//        }
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()

            .addInterceptor { chain ->
                val request : Request = chain.request()
                    .newBuilder()
                    .addHeader("X-Api-Key", ApiManager.API_KEY)
                    .build()
                val response : Response = chain.proceed(request)
                return@addInterceptor response
            }
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideWebServices(retrofit: Retrofit): WebServices {
        return retrofit.create(WebServices::class.java)
    }
}