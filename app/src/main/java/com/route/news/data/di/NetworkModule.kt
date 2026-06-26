package com.route.news.data.di

import com.route.news.data.api.WebServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideWebServices(retrofit: Retrofit): WebServices {
        return retrofit.create(WebServices::class.java)
    }
}