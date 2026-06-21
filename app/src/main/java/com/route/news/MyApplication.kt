package com.route.news

import android.app.Application
import com.route.newsc43.data.database.MyDatabase
import com.route.newsc43.utils.Connectivity

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MyDatabase.createDatabase(this)
        Connectivity.context = this
    }

}
