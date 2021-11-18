package com.example.android2021.kt05
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android2021.R

/**
 * 广播测试demo
 */
class BroadcastActivity : AppCompatActivity() {

    private lateinit var broadcastReceiver: TestBroadcastReceiver

    private lateinit var intentFilter :IntentFilter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)
        broadcastReceiver = TestBroadcastReceiver()
        intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(broadcastReceiver,intentFilter)

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }
}