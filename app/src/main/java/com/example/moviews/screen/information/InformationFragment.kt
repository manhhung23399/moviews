package com.example.moviews.screen.information

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.moviews.R
import com.example.moviews.base.BaseFragment
import com.example.moviews.utils.Notification
import kotlinx.android.synthetic.main.fragment_information.*


class InformationFragment : BaseFragment() {
    private var checkStateSwitch: Boolean? = null
    override val layoutID: Int
        get() = R.layout.fragment_information

    override fun initViews() {
    }

    override fun initData() {
        val preferences: SharedPreferences? =
            context?.getSharedPreferences(PREF_SETTING, MODE_PRIVATE)
        checkStateSwitch = preferences?.getBoolean(PREF_SWITCH, false)
    }

    override fun initEvents() {
        switchNotification.isChecked = checkStateSwitch == true
        switchNotification.setOnCheckedChangeListener { _, isChecked ->
            context?.let {
                if (isChecked) {
                    Notification.enable(it)
                } else {
                    Notification.disable(it)
                }
                val preferences: SharedPreferences? =
                    it.getSharedPreferences(PREF_SETTING, MODE_PRIVATE)
                val editor = preferences?.edit()
                editor?.putBoolean(PREF_SWITCH, isChecked)
                editor?.apply()
            }
        }
    }

    companion object {
        const val PREF_SETTING = "PREF_SETTING"
        const val PREF_SWITCH = "PREF_SWITCH"
        private var instance: InformationFragment? = null
        fun newInstance(): InformationFragment = instance ?: InformationFragment()
            .also { instance = it }
    }
}
