package com.nekobitlz.nimble_torrent.views.dialogs.di

import com.nekobitlz.nimble_torrent.views.base.di.PresenterScope
import com.nekobitlz.nimble_torrent.views.dialogs.DialogManager
import com.nekobitlz.nimble_torrent.views.dialogs.IDialogManager
import dagger.Module
import dagger.Provides

@Module
class DialogManagerModule {

    @PresenterScope
    @Provides
    fun provideDialogManager(): IDialogManager {
        return DialogManager()
    }
}