package com.nekobitlz.nimble_torrent

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.nekobitlz.nimble_torrent.repository.ITorrentRepository
import com.nekobitlz.nimble_torrent.repository.TorrentRepository
import com.nekobitlz.nimble_torrent.repository.database.TorrentDao
import com.nekobitlz.nimble_torrent.repository.database.TorrentDatabase
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, TorrentRepositoryModule::class, DatabaseModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: NimbleApplication)
    fun getTorrentRepository(): ITorrentRepository
}

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application
}


@Module
class TorrentRepositoryModule {

    @Provides
    @Singleton
    fun providesTorrentRepository(dao: TorrentDao): ITorrentRepository {
        return TorrentRepository(dao)
    }
}

@Module
class DatabaseModule {

    private val DB_NAME = "torrent_db"

    @Singleton
    @Provides
    fun provideTorrentDatabase(context: Context): TorrentDatabase {
        return Room.databaseBuilder(context, TorrentDatabase::class.java, DB_NAME).build()
    }

    @Singleton
    @Provides
    fun provideTorrentDao(db: TorrentDatabase): TorrentDao = db.torrentDao()
}
