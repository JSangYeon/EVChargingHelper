package jsy.vehicle.evcharginghelper.util.noti

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import jsy.vehicle.evcharginghelper.EVChargingHelperApplication.Companion.getGlobalApplicationContext
import jsy.vehicle.evcharginghelper.R

class SampleNotificationUtils {

    val CHANNEL_ID = "NotificationSampleChannel"
    private var notificationManager: NotificationManager = getGlobalApplicationContext().getSystemService(
        Context.NOTIFICATION_SERVICE
    ) as NotificationManager
    private var builder : NotificationCompat.Builder =
        NotificationCompat.Builder(getGlobalApplicationContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("12345")
            .setContentText("textContent")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    init {

        createNoti()
    }

    fun useNoti() {
        Log.d("useNoti", "here")

//        notificationManager.notify(123124, builder.build());
//        notificationManager.notify(1555,builder.build())

//        notificationManager.notify(123124, builder.build());
        notificationManager.notify(1555,builder.build())


    }


    private fun createNoti() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d("createNoti", "here")
            val name = getGlobalApplicationContext().getString(R.string.channel_name)
            val descriptionText =
                getGlobalApplicationContext().getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT


            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            notificationManager.createNotificationChannel(channel)
            builder.setChannelId(CHANNEL_ID)

        }
    }
}