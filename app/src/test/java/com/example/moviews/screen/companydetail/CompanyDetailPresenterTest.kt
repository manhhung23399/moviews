package com.example.moviews.screen.companydetail

import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.model.Company
import com.example.moviews.data.model.CompanyDetail
import com.example.moviews.repository.DetailRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Test

class CompanyDetailPresenterTest {
    private val view = mockk<CompanyDetailContract.View>(relaxed = true)
    private val repository = mockk<DetailRepository>()
    private val companyDetailPresenter = CompanyDetailPresenter(view, repository)
    private val callback = slot<OnLoadDataCallback<CompanyDetail>>()

    @Test
    fun `getDetailCompany callback return onSuccess`() {
        val company = Company(0, "", "")
        val companyDetail = CompanyDetail(company, "", "", "")
        every {
            repository.getCompanyDetail(0, capture(callback))
        } answers {
            callback.captured.onSuccess(companyDetail)
        }
        companyDetailPresenter.getDetailCompany(0)
        verify {
            view.showCompanyDetail(companyDetail)
        }
    }

    @Test
    fun `getDetailCompany callback return onError exception not null`() {
        val exception = Exception()
        every {
            repository.getCompanyDetail(0, capture(callback))
        } answers {
            callback.captured.onError(exception)
        }
        companyDetailPresenter.getDetailCompany(0)
        verify {
            view.showError(exception.message.toString())
        }
    }

    @Test
    fun `getDetailCompany callback return onError exception null`() {
        val exception: Exception? = null
        every {
            repository.getCompanyDetail(0, capture(callback))
        } answers {
            callback.captured.onError(exception)
        }
        companyDetailPresenter.getDetailCompany(0)
        verify {
            view.showError(exception?.message.toString())
        }
    }
}
