package com.nekobitlz.nimble_torrent.views.base.mvp

interface BaseContract {

    interface View {
        fun showSuccess(stringId: Int)
        fun showInfo(stringId: Int)
        fun showError(stringId: Int)
        fun setLoading(isLoading: Boolean)
    }

    interface Presenter<in V : View> {
        fun attachView(view: V)
        fun detachView()
    }
}