package com.example.moviews.data.remote

import android.os.AsyncTask
import com.example.moviews.data.OnLoadDataCallback
import org.json.JSONObject

class GetJsonFromUrl<T>(
    private val keyEntity: String,
    private val callback: OnLoadDataCallback<T>
) : AsyncTask<String?, Unit, String?>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg strings: String?): String {
        var data = ""
        try {
            val parseDataWithJson = ParseDataWithJson()
            data = parseDataWithJson.getJsonFromUrl(strings[0])
        } catch (e: Exception) {
            exception = e
        }
        return data
    }

    override fun onPostExecute(data: String?) {
        super.onPostExecute(data)
        if (data != null && data.isNotBlank()) {
            val jsonObject = JSONObject(data)
            @Suppress("UNCHECKED_CAST")
            callback.onSuccess(ParseDataWithJson().parseJsonToData(jsonObject, keyEntity) as T)
        } else exception?.let { callback.onError(it) }
    }
}
