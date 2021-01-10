package com.eugene.logcatdemo

import android.app.Application
import com.eugene.logcatlib.Logcat

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Logcat.initialize(BuildConfig.DEBUG, "com.eugene.logcatdemo")
        Logcat.debug("Application Started!")
    }
}