package com.example.moviews.screen.companydetail

import androidx.core.os.bundleOf
import com.example.moviews.R
import com.example.moviews.base.BaseFragment

class CompanyDetailFragment : BaseFragment() {
    override val layoutID: Int
        get() = R.layout.fragment_company_detail

    override fun initViews() {
    }

    override fun initData() {
    }

    override fun initEvent() {
    }

    companion object {
        private const val BUNDLE_COMPANY_ID = "BUNDLE_COMPANY_ID"
        fun getInstance(idCompany: Int) = CompanyDetailFragment().apply {
            arguments = bundleOf(BUNDLE_COMPANY_ID to idCompany)
        }
    }
}
