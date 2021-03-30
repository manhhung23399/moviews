package com.example.moviews.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String, imageView: ImageView) {
    Glide.with(context).load(url).into(imageView)
}
