package com.nekobitlz.nimble_torrent.views.base.mvp

import rx.Subscriber
import rx.Subscription
import rx.subscriptions.CompositeSubscription

open class BasePresenter<V: BaseContract.View> : BaseContract.Presenter<V> {

    lateinit var view: V
        private set

    private val subscription = CompositeSubscription()

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        subscription.unsubscribe()
    }

    fun Subscription.addSubscription() {
        subscription.add(this)
    }

    abstract inner class BaseSubscriber<V>(val isLoading: Boolean = true) : Subscriber<V>() {
        override fun onCompleted() {
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
    }
}