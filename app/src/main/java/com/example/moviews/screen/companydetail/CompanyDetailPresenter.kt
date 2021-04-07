package com.example.moviews.screen.companydetail

import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.model.CompanyDetail
import com.example.moviews.repository.DetailRepository

class CompanyDetailPresenter(
    private val view: CompanyDetailContract.View,
    private val repository: DetailRepository
) : CompanyDetailContract.Presenter {

    override fun getDetailCompany(idCompany: Int) {
        repository.getCompanyDetail(idCompany, object : OnLoadDataCallback<CompanyDetail> {
            override fun onSuccess(data: CompanyDetail) {
                view.showCompanyDetail(data)
            }

            override fun onError(e: Exception?) {
                view.showError(e?.message.toString())
            }
        })
    }

    override fun onStart() {
    }
}
