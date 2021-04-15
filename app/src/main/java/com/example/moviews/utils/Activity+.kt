package com.example.moviews.utils

import androidx.fragment.app.Fragment
import com.example.moviews.MainActivity
import com.example.moviews.R

fun MainActivity.switchFragment(fragment: Fragment) {
    supportFragmentManager.apply {
        popBackStack()
        beginTransaction()
            .replace(R.id.frameContainer, fragment)
            .commit()
    }
}
