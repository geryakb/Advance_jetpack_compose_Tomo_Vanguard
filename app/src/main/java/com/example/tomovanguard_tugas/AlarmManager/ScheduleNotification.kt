package com.example.tomovanguard_tugas.AlarmManager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import com.example.tomovanguard_tugas.AlarmManager.NotificationKeys.RMNDR_NOTI_ID
import com.example.tomovanguard_tugas.AlarmManager.NotificationKeys.RMNDR_NOTI_TITLE_KEY
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
fun scheduleNotification(
    context: Context,
    timePicker: TimePickerState,
    datePicker: DatePickerState,
    title : String
){
    Log.d("AlarmManager", "Scheduling notification with title: $title")
    val intent = Intent(context.applicationContext, ReminderReceiver::class.java).apply {
        putExtra(RMNDR_NOTI_TITLE_KEY, title)
    }
    val pendingIntent = PendingIntent.getBroadcast(
        context.applicationContext,
        RMNDR_NOTI_ID,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
    )
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val selectedDate = Calendar.getInstance().apply {
        timeInMillis = datePicker.selectedDateMillis!!
    }

    val year = selectedDate.get(Calendar.YEAR)
    val month = selectedDate.get(Calendar.MONTH)
    val day = selectedDate.get(Calendar.DAY_OF_MONTH)

    val calendar = Calendar.getInstance()
    calendar.set(year, month, day, timePicker.hour, timePicker.minute)

    alarmManager.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP,
        calendar.timeInMillis,
        pendingIntent
    )
    Toast.makeText(context, "Alarm berhasil diatur", Toast.LENGTH_SHORT).show()
}