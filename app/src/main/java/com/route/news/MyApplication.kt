package com.route.news

import android.app.Application
import com.route.newsc43.data.database.MyDatabase


import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}
