package com.nekobitlz.nimble_torrent.views.fragments.alltorrents.di

import com.nekobitlz.nimble_torrent.AppComponent
import com.nekobitlz.nimble_torrent.repository.ITorrentRepository
import com.nekobitlz.nimble_torrent.views.base.di.PresenterScope
import com.nekobitlz.nimble_torrent.views.dialogs.di.DialogManagerModule
import com.nekobitlz.nimble_torrent.views.fragments.alltorrents.AllTorrentsContract
import com.nekobitlz.nimble_torrent.views.fragments.alltorrents.AllTorrentsFragment
import com.nekobitlz.nimble_torrent.views.fragments.alltorrents.AllTorrentsPresenter
import dagger.Component
import dagger.Module
import dagger.Provides

@PresenterScope
@Component(modules = [AllTorrentsModule::class, DialogManagerModule::class], dependencies = [AppComponent::class])
interface AllTorrentsComponent {
    fun inject(allTorrentsFragment: AllTorrentsFragment)
}

@Module
class AllTorrentsModule {

    @Provides
    @PresenterScope
    fun providesAllTorrentsPresenter(torrentRepository: ITorrentRepository): AllTorrentsContract.Presenter {
        return AllTorrentsPresenter(torrentRepository)
    }
}