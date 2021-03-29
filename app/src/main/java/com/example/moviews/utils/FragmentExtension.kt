package com.example.moviews.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.moviews.R
import com.example.moviews.screen.moviedetail.MovieDetailFragment

fun Fragment.addFragment(idMovie: Int) {
    activity?.run {
        val fragment = MovieDetailFragment.getInstance(idMovie)
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.frameContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}

fun Fragment.showToast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
