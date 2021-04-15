package com.example.moviews.screen.castdetail

import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.model.CastDetail
import com.example.moviews.repository.DetailRepository

class CastDetailPresenter(
    private val view: CastDetailContract.View,
    private val repository: DetailRepository
) : CastDetailContract.Presenter {

    override fun getCastDetail(idCast: Int) {
        view.showLoading()
        repository.getCastDetail(idCast, object : OnLoadDataCallback<CastDetail> {
            override fun onSuccess(data: CastDetail) {
                view.run {
                    showCastDetail(data)
                    showMovies(data.movies)
                    hideLoading()
                }
            }

            override fun onError(e: Exception?) {
                view.apply {
                    showError(e?.message.toString())
                    hideLoading()
                }
            }
        })
    }

    override fun onStart() {
    }
}
