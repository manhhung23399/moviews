package com.example.moviews.screen.companydetail

import androidx.core.os.bundleOf
import com.example.moviews.R
import com.example.moviews.base.BaseFragment
import com.example.moviews.data.model.CompanyDetail
import com.example.moviews.repository.RepositoryUtils
import com.example.moviews.utils.BaseUrl.baseUrlImage
import com.example.moviews.utils.LoadingDialog
import com.example.moviews.utils.loadImage
import kotlinx.android.synthetic.main.fragment_company_detail.*

class CompanyDetailFragment : BaseFragment(), CompanyDetailContract.View {
    private var loadingDialog: LoadingDialog? = null
    private var presenter: CompanyDetailPresenter? = null
    override val layoutID: Int
        get() = R.layout.fragment_company_detail

    override fun initViews() {
        initDialog()
    }

    override fun initData() {
        context?.let {
            val companyDetailPresenter = RepositoryUtils.getDetailRepository(it)
            presenter = CompanyDetailPresenter(this, companyDetailPresenter)
        }
        arguments?.let { presenter?.getDetailCompany(it.getInt(BUNDLE_COMPANY_ID)) }
    }

    override fun initEvents() {
        imageBackCompanyDetail.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun showCompanyDetail(companyDetail: CompanyDetail) {
        textNameCompany.append(companyDetail.company.name)
        textHeadquarters.append(companyDetail.headquarter)
        textHomePage.append(companyDetail.homePage)
        textCountry.append(companyDetail.country)
        imageLogoCompany.loadImage(baseUrlImage(companyDetail.company.logo))
    }

    override fun showError(message: String) {
        showError(message)
    }

    override fun showLoading() {
        loadingDialog?.show()
    }

    override fun hideLoading() {
        loadingDialog?.dismiss()
    }
    private fun initDialog() {
        context?.let { loadingDialog = LoadingDialog(it) }
    }

    companion object {
        private const val BUNDLE_COMPANY_ID = "BUNDLE_COMPANY_ID"
        fun getInstance(idCompany: Int) = CompanyDetailFragment().apply {
            arguments = bundleOf(BUNDLE_COMPANY_ID to idCompany)
        }
    }
}
