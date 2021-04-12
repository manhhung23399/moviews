package com.example.moviews.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.moviews.service.NotificationService

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        NotificationService.enqueueWork(context, intent)
    }
}
