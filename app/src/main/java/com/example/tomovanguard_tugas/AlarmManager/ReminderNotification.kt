package com.example.tomovanguard_tugas.AlarmManager

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.core.app.NotificationCompat
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.tomovanguard_tugas.AlarmManager.NotificationKeys.RMNDR_NOTI_CHNNL_ID
import com.example.tomovanguard_tugas.AlarmManager.NotificationKeys.RMNDR_NOTI_ID
import com.example.tomovanguard_tugas.R

class ReminderNotification(
    private val context : Context
) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)
    fun sendReminderNotification(title : String?){
        val notification = NotificationCompat.Builder(context, RMNDR_NOTI_CHNNL_ID)
            .setContentTitle(title)
            .setContentText(context.getString(R.string.app_name))
            .setSmallIcon(R.drawable.alarm_icon)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.resources,
                    R.raw.icon_alarm
                )
            )
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(context.bitmapFromResource(R.drawable.ic_launcher_foreground))
            )
            .setAutoCancel(true)
            .build()
        notificationManager.notify(RMNDR_NOTI_ID, notification)
    }
    private fun Context.bitmapFromResource(
        @DrawableRes resId: Int
    ) = BitmapFactory.decodeResource(
        resources,
        resId
    )
}