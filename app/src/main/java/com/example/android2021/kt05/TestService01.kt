package com.example.android2021.kt05

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.android2021.utils.EasyLog

class TestService01 : Service() {

    companion object{
        const val TAG = "TestService01"
    }
    override fun onBind(intent: Intent): IBinder? {
       return null
        EasyLog.e(TAG,"onBind")
    }

    override fun onCreate() {
        super.onCreate()
        EasyLog.e(TAG,"onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        EasyLog.e(TAG,"onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        EasyLog.e(TAG,"onDestroy")
    }


}