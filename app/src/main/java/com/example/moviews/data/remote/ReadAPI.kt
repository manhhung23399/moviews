package com.example.moviews.data.remote

import com.example.moviews.utils.Constant
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

fun readApi(urlString: String): String {
    val url = URL(urlString)
    val httpURLConnection = url.openConnection() as HttpURLConnection
    httpURLConnection.apply {
        connectTimeout = Constant.BASE_TIME_OUT
        readTimeout = Constant.BASE_TIME_OUT
        requestMethod = Constant.BASE_METHOD_GET
        doOutput = true
        connect()
    }
    val bufferedReader = BufferedReader(InputStreamReader(url.openStream()))
    val stringBuilder = StringBuilder()
    var line: String?
    while (bufferedReader.readLine().also { line = it } != null) {
        stringBuilder.append(line)
    }
    bufferedReader.close()
    httpURLConnection.disconnect()
    return stringBuilder.toString()
}
