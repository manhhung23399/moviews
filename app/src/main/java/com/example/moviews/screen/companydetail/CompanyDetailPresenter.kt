package com.example.moviews.screen.companydetail

import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.model.CompanyDetail
import com.example.moviews.repository.DetailRepository

class CompanyDetailPresenter(
    private val view: CompanyDetailContract.View,
    private val repository: DetailRepository
) : CompanyDetailContract.Presenter {

    override fun getDetailCompany(idCompany: Int) {
        view.showLoading()
        repository.getCompanyDetail(idCompany, object : OnLoadDataCallback<CompanyDetail> {
            override fun onSuccess(data: CompanyDetail) {
                view.showCompanyDetail(data)
                view.hideLoading()
            }

            override fun onError(e: Exception?) {
                view.showError(e?.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun onStart() {
    }
}
