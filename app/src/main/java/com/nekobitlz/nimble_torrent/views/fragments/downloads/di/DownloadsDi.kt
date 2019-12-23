package com.nekobitlz.nimble_torrent.views.fragments.downloads.di

import com.nekobitlz.nimble_torrent.AppComponent
import com.nekobitlz.nimble_torrent.repository.ITorrentRepository
import com.nekobitlz.nimble_torrent.views.base.di.PresenterScope
import com.nekobitlz.nimble_torrent.views.fragments.downloads.DownloadsContract
import com.nekobitlz.nimble_torrent.views.fragments.downloads.DownloadsFragment
import com.nekobitlz.nimble_torrent.views.fragments.downloads.DownloadsPresenter
import dagger.Component
import dagger.Module
import dagger.Provides

@PresenterScope
@Component(modules = [DownloadsModule::class], dependencies = [AppComponent::class])
interface DownloadsComponent {
    fun inject(downloadsFragment: DownloadsFragment)
}

@Module
class DownloadsModule {

    @Provides
    @PresenterScope
    fun providesDownloadsPresenter(torrentRepository: ITorrentRepository): DownloadsContract.Presenter {
        return DownloadsPresenter(torrentRepository)
    }
}