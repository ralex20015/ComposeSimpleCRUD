package com.earl.simplecrud

import android.app.Application
import com.earl.simplecrud.db.DatabaseProvider
import com.earl.simplecrud.db.UserRepository
import com.earl.simplecrud.signinsignup.SessionRepository

class MyApplication : Application() {

    companion object {
        lateinit var sessionRepository: SessionRepository
    }

    override fun onCreate() {
        super.onCreate()
        DatabaseProvider.initialize(this)
        sessionRepository = SessionRepository
    }

}