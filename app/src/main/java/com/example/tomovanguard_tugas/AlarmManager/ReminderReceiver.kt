package com.example.tomovanguard_tugas.AlarmManager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.tomovanguard_tugas.AlarmManager.NotificationKeys.RMNDR_NOTI_TITLE_KEY

class ReminderReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val scheduleNotificationService = context?.let { ReminderNotification(it) }
        val title: String = intent?.getStringExtra(RMNDR_NOTI_TITLE_KEY) ?: return
        Log.d("ReminderReceiver", "Alarm received with title: $title")
        scheduleNotificationService?.sendReminderNotification(title)

    }
}