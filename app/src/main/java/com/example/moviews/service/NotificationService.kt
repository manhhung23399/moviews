package com.example.moviews.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.JobIntentService
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.NotificationTarget
import com.example.moviews.MainActivity
import com.example.moviews.R
import com.example.moviews.data.OnLoadDataCallback
import com.example.moviews.data.model.Movie
import com.example.moviews.repository.RepositoryUtils
import com.example.moviews.utils.BaseUrl
import com.example.moviews.utils.Constant

class NotificationService : JobIntentService() {

    override fun onHandleWork(intent: Intent) {
        val movieRepository = RepositoryUtils.getMovieRepository(applicationContext)
        movieRepository.getMovies(
            Constant.BASE_TRENDING,
            object : OnLoadDataCallback<MutableList<Movie>> {
                override fun onSuccess(data: MutableList<Movie>) {
                    showNotification(data.random())
                }

                override fun onError(e: Exception?) {
                }
            })
    }

    private fun createChannel() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun loadImageToNotification(
        notification: Notification,
        views: RemoteViews,
        movie: Movie
    ) {
        val target = NotificationTarget(
            this,
            R.id.imageNotify,
            views,
            notification,
            NOTIFICATION_ID
        )
        Glide
            .with(this)
            .asBitmap()
            .load(BaseUrl.baseUrlImage(movie.poster))
            .into(target)

    }

    private fun showNotification(movie: Movie) {
        createChannel()
        val collapsedView = RemoteViews(
            packageName,
            R.layout.layout_notification_collapsed
        )
        val expandedView = RemoteViews(
            packageName,
            R.layout.layout_notification_expanded
        )
        expandedView.setTextViewText(R.id.textTitleMovieNotify, movie.title)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, getIntent(this), PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_star)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(collapsedView)
            .setCustomBigContentView(expandedView)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()
        val notificationManagerCompat = NotificationManagerCompat.from(this)
        notificationManagerCompat.notify(NOTIFICATION_ID, builder)
        loadImageToNotification(builder, expandedView, movie)
    }

    companion object {
        private const val CHANNEL_ID = "NotifyMovieChannel"
        private const val JOB_ID = 1
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_NAME = "Channel1"
        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, NotificationService::class.java, JOB_ID, intent)
        }

        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
