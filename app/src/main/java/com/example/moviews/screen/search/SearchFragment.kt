package com.example.moviews.screen.search

import com.example.moviews.R
import com.example.moviews.base.BaseFragment

class SearchFragment : BaseFragment() {

    override val layoutID: Int
        get() = R.layout.fragment_search

    override fun initViews() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

    companion object {
        private var instance: SearchFragment? = null
        fun newInstance(): SearchFragment = instance ?: SearchFragment()
            .also { instance = it }
    }
}
