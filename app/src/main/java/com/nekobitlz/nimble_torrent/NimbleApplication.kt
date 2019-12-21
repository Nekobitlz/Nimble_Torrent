package com.nekobitlz.nimble_torrent

import androidx.multidex.MultiDexApplication

class NimbleApplication : MultiDexApplication() {

    internal val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}
