package com.nekobitlz.nimble_torrent.views.base.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.DisposableSubscriber

open class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {

    lateinit var view: V
        private set

    private val subscription = CompositeDisposable()

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        subscription.dispose()
    }

    abstract inner class BaseSubscriber<V>(private val isLoading: Boolean = true) : DisposableSubscriber<V>() {
        override fun onComplete() {
            if (isLoading) {
                view.setLoading(false)
            }
        }

        override fun onStart() {
            if (isLoading) view.setLoading(true)
            super.onStart()
        }

        override fun onError(e: Throwable?) {
            e?.printStackTrace()
        }

        fun addSubscription() {
            subscription.add(this)
        }
    }
}