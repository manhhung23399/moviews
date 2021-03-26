package com.example.moviews.data.local

import android.os.AsyncTask
import com.example.moviews.data.OnLoadDataCallback
import java.lang.Exception

class LocalAsyncTask<P, T>(
    private val callback: OnLoadDataCallback<T>,
    private val handler: (P) -> T
) : AsyncTask<P, Unit, T?>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg params: P): T? =
        try {
            handler(params[0])
        } catch (exception: Exception) {
            this.exception = exception
            null
        }

    override fun onPostExecute(result: T?) {
        result?.let(callback::onSuccess) ?: (callback::onError)
    }
}
