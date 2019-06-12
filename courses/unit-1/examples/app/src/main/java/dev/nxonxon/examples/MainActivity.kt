package dev.nxonxon.examples

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import dev.nxonxon.examples.services.MyService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate")

        btnStartService.setOnClickListener {
            // Foreground service call
//            val intent = Intent(this, ForegroundService::class.java)
//            intent.action = AppConstants.STARTFOREGROUND_ACTION
//            startService(intent)
        }

        btnStopService.setOnClickListener {
            // Foreground service call
//            val intent = Intent(this, ForegroundService::class.java)
//            intent.action = AppConstants.STOPFOREGROUND_ACTION
//            stopService(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    companion object {
        val TAG: String = "###${MainActivity::class.java.simpleName}"
    }
}