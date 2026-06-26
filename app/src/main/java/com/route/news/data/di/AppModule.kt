package com.route.news.data.di

import com.route.news.data.repositories.news_repository.NewsRepositoryImp
import com.route.news.data.repositories.news_repository.data_sources.news_local_data_source.NewsLocalDataSource
import com.route.news.data.repositories.news_repository.data_sources.news_local_data_source.NewsLocalDataSourceImp
import com.route.news.data.repositories.news_repository.data_sources.news_remote_data_source.NewsRemoteDataSource
import com.route.news.data.repositories.news_repository.data_sources.news_remote_data_source.NewsRemoteDataSourceImp
import com.route.news.domain.repository.NewsRepository
import com.route.news.utils.Connectivity
import com.route.news.utils.ConnectivityImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindNewsRepo(arg: NewsRepositoryImp): NewsRepository
    @Binds
    abstract fun bindNewsLocalDataSource(arg: NewsLocalDataSourceImp): NewsLocalDataSource
    @Binds
    abstract fun bindNewsRemoteDataSource(arg: NewsRemoteDataSourceImp): NewsRemoteDataSource
    @Binds
    abstract fun bindConnectivity(arg: ConnectivityImpl): Connectivity
}