package com.nekobitlz.nimble_torrent.views.fragments.downloads.di

import com.nekobitlz.nimble_torrent.repository.ITorrentRepository
import com.nekobitlz.nimble_torrent.repository.TorrentRepository
import com.nekobitlz.nimble_torrent.views.base.di.PresenterScope
import com.nekobitlz.nimble_torrent.views.fragments.downloads.DownloadsContract
import com.nekobitlz.nimble_torrent.views.fragments.downloads.DownloadsFragment
import com.nekobitlz.nimble_torrent.views.fragments.downloads.DownloadsPresenter
import dagger.Component
import dagger.Module
import dagger.Provides

@PresenterScope
@Component(modules = [DownloadsModule::class])
interface DownloadsComponent {
    fun inject(downloadsFragment: DownloadsFragment)
}

@Module(includes = [PresenterModule::class])
class DownloadsModule {

    @Provides
    @PresenterScope
    fun providesDownloadsPresenter(torrentRepository: ITorrentRepository): DownloadsContract.Presenter {
        return DownloadsPresenter(torrentRepository)
    }
}

@Module
class PresenterModule {
    @Provides
    @PresenterScope
    fun providesRepository(): ITorrentRepository {
        return TorrentRepository()
    }
}
