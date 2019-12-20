package com.nekobitlz.nimble_torrent

import android.content.Context
import com.nekobitlz.nimble_torrent.views.base.di.AppContext
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AndroidInjector<NimbleApplication> {

    @Component.Factory
    abstract class Builder : AndroidInjector.Factory<NimbleApplication>
}

@Module(includes = [AndroidInjectionModule::class])
abstract class AppModule {

    @Singleton
    @Binds
    @AppContext
    abstract fun provideContext(application: NimbleApplication): Context
}