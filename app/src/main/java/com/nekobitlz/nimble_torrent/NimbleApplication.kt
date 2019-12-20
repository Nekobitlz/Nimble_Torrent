package com.nekobitlz.nimble_torrent

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class NimbleApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}