package com.earl.simplecrud

import android.app.Application

class MyApplication : Application() {
    private lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        database = AppDatabase.getInstance(this)
    }
}