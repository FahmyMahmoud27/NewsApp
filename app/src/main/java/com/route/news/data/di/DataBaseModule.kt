package com.route.news.data.di

import android.content.Context
import androidx.room.Room
import com.route.news.data.database.dao.SourcesDao
import com.route.newsc43.data.database.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    fun provideMyDatabase(@ApplicationContext context: Context): MyDatabase {
        return Room.databaseBuilder(context, MyDatabase::class.java, "todo-db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideDao(myDatabase: MyDatabase): SourcesDao {
        return myDatabase.getSourcesDao()
    }
}