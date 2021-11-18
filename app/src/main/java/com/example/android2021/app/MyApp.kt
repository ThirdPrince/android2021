package com.example.android2021.app

import android.app.Application
import com.blankj.utilcode.util.CrashUtils

/**
 * @Title: $
 * @Package $
 * @Description:
 * @author $
 * @date $
 * @version V1.0
 */
class MyApp :Application() {

    override fun onCreate() {
        super.onCreate()
        CrashUtils.init()
    }
}