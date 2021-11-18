package com.example.android2021.utils

import android.util.Log
import com.example.android2021.BuildConfig

/**
 * @Title: $
 * @Package $
 * @Description: Log 封装
 * @author dhl
 * @date 2021 11 21
 * @version V1.0
 */
object EasyLog {

    fun e(tag:String,msg:String){
        if(BuildConfig.DEBUG){
            Log.e(tag,msg)
        }
    }
}