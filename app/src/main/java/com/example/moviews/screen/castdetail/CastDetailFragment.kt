package com.example.moviews.screen.castdetail

import androidx.core.os.bundleOf
import com.example.moviews.R
import com.example.moviews.base.BaseFragment

class CastDetailFragment : BaseFragment() {
    override val layoutID: Int
        get() = R.layout.fragment_cast_detail

    override fun initViews() {
    }

    override fun initData() {
    }

    override fun initEvent() {
    }

    companion object {
        private const val BUNDLE_CAST_ID = "BUNDLE_CAST_ID"
        fun getInstance(idCast: Int) = CastDetailFragment().apply {
            arguments = bundleOf(BUNDLE_CAST_ID to idCast)
        }
    }
}
