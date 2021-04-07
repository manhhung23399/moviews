package com.example.moviews.screen.companydetail

import com.example.moviews.base.BasePresenter
import com.example.moviews.data.model.CompanyDetail

interface CompanyDetailContract {
    interface View {
        fun showCompanyDetail(companyDetail: CompanyDetail)
        fun showError(message: String)
    }

    interface Presenter : BasePresenter<View> {
        fun getDetailCompany(idCompany: Int)
    }
}
