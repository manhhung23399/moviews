package com.example.moviews.screen.information

import com.example.moviews.R
import com.example.moviews.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_information.*

class InformationFragment : BaseFragment() {

    override val layoutID: Int
        get() = R.layout.fragment_information

    override fun initViews() {
    }

    override fun initData() {
    }

    override fun initEvent() {
        switchNotification.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
            } else {
            }
        }
    }

    companion object {
        private var instance: InformationFragment? = null
        fun newInstance(): InformationFragment = instance ?: InformationFragment()
            .also { instance = it }
    }
}
