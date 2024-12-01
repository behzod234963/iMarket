package com.mr.anonym.imarket.di.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class IMarketApp:Application(){

    override fun onCreate() {
        super.onCreate()
    }
}