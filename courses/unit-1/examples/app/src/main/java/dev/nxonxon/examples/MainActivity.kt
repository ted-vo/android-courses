package dev.nxonxon.examples

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import dev.nxonxon.examples.services.ForegroundService
import dev.nxonxon.examples.services.MyService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBoundService: MyService
    private var mBound: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate")

        btnBoundService.setOnClickListener {
            groupBoundService.visibility = View.VISIBLE
            groupUnBoundService.visibility = View.GONE
        }

        btnUnBoundService.setOnClickListener {
            groupBoundService.visibility = View.GONE
            groupUnBoundService.visibility = View.VISIBLE
        }

        // Bound Service
        val serviceConnection = getServiceConnection()
        btnStartBoundService.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }

        btnStopBoundService.setOnClickListener {
            unbindService(serviceConnection)
            mBound = false
        }

        btnGetNumber.setOnClickListener {
            if (mBound) {
                val number = mBoundService.randomNumber
                Toast.makeText(this, "Number: $number", Toast.LENGTH_LONG).show()
            }
        }
        // End Bound Service

        // UnBound Service
        btnStartService.setOnClickListener {
            // Foreground service call
            val intent = Intent(this, ForegroundService::class.java)
            intent.action = AppConstants.STARTFOREGROUND_ACTION
            startService(intent)
        }

        btnStopService.setOnClickListener {
            // Foreground service call
            val intent = Intent(this, ForegroundService::class.java)
            intent.action = AppConstants.STOPFOREGROUND_ACTION
            stopService(intent)
        }
        // End UnBound Service

        btnTestActivity.setOnClickListener {
            startActivity(Intent(this, TestDBActivity::class.java))
            //finish()
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

    private fun getServiceConnection(): ServiceConnection {
        return object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                val binder = service as MyService.LocalBinder
                mBoundService = binder.getService()
                mBound = true
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                mBound = false
            }
        }
    }

    companion object {
        val TAG: String = "###${MainActivity::class.java.simpleName}"
    }
}