package com.example.moviews.screen.error

import android.content.Context
import android.net.ConnectivityManager
import androidx.fragment.app.FragmentTransaction
import com.example.moviews.R
import com.example.moviews.base.BaseFragment
import com.example.moviews.screen.home.HomeFragment
import com.example.moviews.utils.addFragment
import kotlinx.android.synthetic.main.fragment_error.*

class ErrorFragment : BaseFragment() {
    override val layoutID: Int
        get() = R.layout.fragment_error

    override fun initViews() {
    }

    override fun initData() {
    }

    override fun initEvents() {
        chipRetry.setOnClickListener {
            if (checkInternet()) {
                activity?.run {
                    supportFragmentManager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.frameContainer, HomeFragment.newInstance())
                        .commit()
                }
            }
        }
    }

    private fun checkInternet(): Boolean {
        val connectivityManager =
            activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    companion object {
        private var instance: ErrorFragment? = null
        fun getInstance(): ErrorFragment = instance ?: ErrorFragment().also { instance = it }
    }
}
