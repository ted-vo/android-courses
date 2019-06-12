package dev.nxonxon.examples.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    // indicates how to behave if the service is killed
    var mStartMode: Int = START_STICKY

    // interface for clients that bind
    var mBinder: IBinder? = null

    // indicates whether onRebind should be used
    var mAllowRebind: Boolean = false

    // Called when the service is being created.
    override fun onCreate() {
        super.onCreate()
        Log.d("### dev", "My service created")
    }

    // The service is starting, due to a call to startService()
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("### dev", "My service started")
        return mStartMode
    }

    // A client is binding to the service with bindService()
    override fun onBind(intent: Intent?): IBinder? {
        Log.d("### dev", "My service bind-ed")
        return mBinder
    }

    // Called when all clients have unbound with unbindService()
    override fun onUnbind(intent: Intent?): Boolean {
        Log.d("### dev", "My service unbind-ed")
        return mAllowRebind
    }

    // Called when a client is binding to the service with bindService()
    override fun onRebind(intent: Intent?) {
        Log.d("### dev", "My service rebind-ed")
    }

    // Called when The service is no longer used and is being destroyed
    override fun onDestroy() {
        super.onDestroy()
        Log.d("### dev", "My service destroyed")
    }
}