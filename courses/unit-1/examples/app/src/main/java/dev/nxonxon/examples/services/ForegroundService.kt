package dev.nxonxon.examples.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import dev.nxonxon.examples.AppConstants
import dev.nxonxon.examples.MainActivity
import dev.nxonxon.examples.R
import dev.nxonxon.examples.receivers.MyReceiver

class ForegroundService : Service() {

    lateinit var mReceiver: MyReceiver

    override fun onBind(intent: Intent?): IBinder? {
        // Used only in case of bound services.
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mReceiver = MyReceiver()
        Log.d("### dev", "Foreground services onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            AppConstants.STARTFOREGROUND_ACTION -> {
                Log.d("### dev", "Received start foreground service intent ");

                val screenStateFilter = IntentFilter()
                screenStateFilter.addAction(Intent.ACTION_SCREEN_ON)
                screenStateFilter.addAction(Intent.ACTION_SCREEN_OFF)
                registerReceiver(mReceiver, screenStateFilter)

                val notificationIntent = Intent(this, MainActivity::class.java).apply {
                    action = Intent.ACTION_MAIN
                    setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }
                val pendingIntent = PendingIntent.getActivity(
                    this,
                    0,
                    notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
                val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val notificationCompat = NotificationCompat.Builder(this, "1")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Foreground Service")
                    .setContentText("Hello world")
                    .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round))
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent)
                    .build()

                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val chanel = NotificationChannel(
                        "1",
                        getString(R.string.app_name),
                        NotificationManager.IMPORTANCE_DEFAULT
                    )
                    notificationManager.createNotificationChannel(chanel)
                }

                startForeground(101, notificationCompat)
            }
            AppConstants.STOPFOREGROUND_ACTION -> {
                Log.d("### dev", "Received stop foreground service intent")
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mReceiver)
        Log.d("### dev", "Foreground services onDestroy")
    }
}