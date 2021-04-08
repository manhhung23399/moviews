package com.example.moviews.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.moviews.R

fun ImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .error(R.drawable.image_error)
        .into(this)
}
