package com.example.moviews.screen.castdetail

import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.model.CastDetail
import com.example.moviews.repository.DetailRepository

class CastDetailPresenter(
    private val view: CastDetailContract.View,
    private val repository: DetailRepository
) : CastDetailContract.Presenter {

    override fun getCastDetail(idCast: Int) {
        repository.getCastDetail(idCast, object : OnLoadDataCallback<CastDetail> {
            override fun onSuccess(data: CastDetail) {
                view.run {
                    showCastDetail(data)
                    showMovies(data.movies)
                }
            }

            override fun onError(e: Exception?) {
                view.showError(e?.message.toString())
            }
        })
    }

    override fun onStart() {
    }
}
