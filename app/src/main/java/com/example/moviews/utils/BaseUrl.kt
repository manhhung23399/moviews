package com.example.moviews.utils

import java.lang.StringBuilder

object BaseUrl {
    fun baseUrlImage(string:String) = StringBuilder(Constant.BASE_URL_IMAGE).append(string).toString()
}
