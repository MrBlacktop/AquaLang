package com.example.aqualang

import android.app.Application
import com.example.aqualang.di.AppComponent
import com.example.aqualang.di.DaggerAppComponent
import com.example.data.database.DatabaseModule

class AquaLangApplication : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().databaseModule(DatabaseModule(this)).build()
    }
}