package com.example.android2021.kt05

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import com.example.android2021.R
import com.example.android2021.utils.EasyLog
import com.google.android.material.button.MaterialButton

class TestServiceActivity : AppCompatActivity() {

    companion object {
        const val TAG = "TestServiceActivity"

    }

    private val btn_01: MaterialButton by lazy {
        findViewById(R.id.btn_01)
    }

    private val btn_02: MaterialButton by lazy {
        findViewById(R.id.btn_02)
    }

    private val btn_03: MaterialButton by lazy {
        findViewById(R.id.btn_03)
    }

    private val btn_04: MaterialButton by lazy {
        findViewById(R.id.btn_04)
    }

    private lateinit var binder: TestService02.MyBinder

    private val conn: ServiceConnection = object : ServiceConnection {
        //Activity与Service断开连接时回调该方法
        override fun onServiceDisconnected(name: ComponentName) {

            EasyLog.e(TAG, "Service DisConnected")
        }

        //Activity与Service连接成功时回调该方法
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            EasyLog.e(TAG, "onServiceConnected")
            binder = service as TestService02.MyBinder
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_service)
        btn_01.setOnClickListener {
            val intent = Intent(applicationContext, TestService01::class.java)
            startService(intent)
        }
        btn_02.setOnClickListener {
            val intent = Intent(applicationContext, TestService01::class.java)
            stopService(intent)
        }

        btn_03.setOnClickListener {
            val intent = Intent(applicationContext, TestService02::class.java)
            bindService(intent, conn, Context.BIND_AUTO_CREATE)
            Handler().postDelayed({
                btn_03.text = binder?.getCount().toString()
            }, 4000)
            // btn_02.text = binder?.getCount().toString()
        }

        btn_04.setOnClickListener {
            unbindService(conn)
        }
    }
}