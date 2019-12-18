package com.nekobitlz.nimble_torrent.views.base.mvp

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

class BasePresenter<V: BaseContract.View> : BaseContract.Presenter<V> {

    lateinit var view: V
        private set

    private val disposables = CompositeDisposable()

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        disposables.clear()
    }

    fun Disposable.addDisposables() {
        disposables.add(this)
    }
}