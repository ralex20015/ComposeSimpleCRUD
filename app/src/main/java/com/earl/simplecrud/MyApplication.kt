package com.earl.simplecrud

import android.app.Application
import com.earl.simplecrud.db.UserRepository

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        UserRepository.initialize(this)
    }

}