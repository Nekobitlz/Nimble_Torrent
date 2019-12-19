package com.nekobitlz.nimble_torrent.views.main.di

import com.nekobitlz.nimble_torrent.views.base.di.PresenterScope
import com.nekobitlz.nimble_torrent.views.main.MainActivity
import com.nekobitlz.nimble_torrent.views.main.MainContract
import com.nekobitlz.nimble_torrent.views.main.MainPresenter
import dagger.Component
import dagger.Module
import dagger.Provides

@PresenterScope
@Component(modules = [MainModule::class])
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}

@Module
class MainModule {
    @Provides
    @PresenterScope
    internal fun providesMainPresenter(): MainContract.Presenter = MainPresenter()
}