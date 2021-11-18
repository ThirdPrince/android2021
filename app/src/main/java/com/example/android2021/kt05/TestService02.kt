package com.example.android2021.kt05

import android.app.Service
import android.content.Intent
import android.icu.lang.UCharacter
import android.os.Binder
import android.os.IBinder
import com.example.android2021.utils.EasyLog
import java.lang.Thread.sleep
import kotlin.concurrent.thread

class TestService02 : Service() {


    companion object{
        const val TAG = "TestService02"
    }
    private var count = 0

    private var quit = false

    private val binder:MyBinder = MyBinder()


    inner class MyBinder:Binder(){
        fun getCount():Int{
            return count
        }
    }
    override fun onBind(intent: Intent): IBinder {
       return binder
    }

    override fun onCreate() {
        super.onCreate()
        thread {
            while(!quit){
                try {
                    sleep(1000)
                }catch (e:InterruptedException){

                }
                count++

            }
        }
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return true
    }


    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        EasyLog.e(TAG,"onRebind")
    }
    override fun onDestroy() {
        super.onDestroy()
        quit = true
        EasyLog.e(TAG,"onDestroy")
    }
}