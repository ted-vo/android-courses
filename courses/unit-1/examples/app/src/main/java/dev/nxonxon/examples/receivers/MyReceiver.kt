package dev.nxonxon.examples.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_SCREEN_ON -> {
                Toast.makeText(context, "Intent screen on detected.", Toast.LENGTH_LONG).show()
                Log.w("### dev", "Intent screen on detected.")
            }
            Intent.ACTION_SCREEN_OFF -> {
                Toast.makeText(context, "Intent screen off detected.", Toast.LENGTH_LONG).show()
                Log.w("### dev", "Intent screen off detected.")
            }
            Intent.ACTION_SHUTDOWN -> {
                Toast.makeText(context, "Intent shutdown detected.", Toast.LENGTH_LONG).show()
                Log.w("### dev", "Intent shutdown detected.")
            }
            Intent.ACTION_BOOT_COMPLETED -> {
                Toast.makeText(context, "Intent boot completed detected.", Toast.LENGTH_LONG).show()
                Log.w("### dev", "Intent boot completed detected.")
            }
        }
    }
}