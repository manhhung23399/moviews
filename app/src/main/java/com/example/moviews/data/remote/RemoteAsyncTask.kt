package com.example.moviews.data.remote

import android.os.AsyncTask
import com.example.moviews.data.OnLoadDataCallback

class RemoteAsyncTask<T>(
    private val callback: OnLoadDataCallback<T>,
    private val handler: () -> T
) : AsyncTask<Unit, Unit, T?>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg params: Unit?): T? =
        try {
            handler()
        } catch (e: Exception) {
            exception = e
            null
        }

    override fun onPostExecute(result: T?) {
        result?.let(callback::onSuccess) ?: (callback.onError(exception))
    }
}
