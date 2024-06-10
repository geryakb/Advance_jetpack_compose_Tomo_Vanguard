package com.example.tomovanguard_tugas.AlarmManager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.tomovanguard_tugas.AlarmManager.NotificationKeys.RMNDR_NOTI_ID

fun cancelNotification(context: Context){
    val intent = Intent(context.applicationContext, ReminderReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context.applicationContext,
        RMNDR_NOTI_ID,
        intent,
        PendingIntent.FLAG_MUTABLE
    )
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.cancel(pendingIntent)
    Toast.makeText(context, "Alarm Telah Dibatalkan", Toast.LENGTH_SHORT).show()
}