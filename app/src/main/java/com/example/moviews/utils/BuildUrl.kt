package com.example.moviews.utils

import android.net.Uri
import com.example.moviews.BuildConfig

fun buildUrl(paths: List<String> = emptyList(), queries: Map<String, String> = emptyMap()) =
    Uri.Builder()
        .scheme(Constant.BASE_HTTPS)
        .authority(Constant.BASE_AUTHORITY)
        .appendPath(Constant.BASE_VERSION)
        .apply {
            paths.forEach {
                appendPath(it)
            }
        }
        .appendQueryParameter(Constant.BASE_KEY, BuildConfig.API_KEY)
        .apply {
            queries.forEach { (k, v) ->
               appendQueryParameter(k, v)
            }
        }
        .toString()
