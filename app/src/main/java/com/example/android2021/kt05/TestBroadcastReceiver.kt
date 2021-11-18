package com.example.android2021.kt05

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

/**
 * @Title: $
 * @Package $
 * @Description: $(用一句话描述)
 * @author $
 * @date $
 * @version V1.0
 */
class TestBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val action = intent?.action
        if(action == ConnectivityManager.CONNECTIVITY_ACTION){
            val connectivityManager:ConnectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val info = connectivityManager.activeNetworkInfo
            if(info != null && info.isAvailable){
                val typeName = info.typeName
                Toast.makeText(context,"当前网络名称：${typeName}",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context,"当前无网络连接",Toast.LENGTH_LONG).show()
            }
        }

    }
}