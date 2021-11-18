package com.example.android2021.kt04

import com.example.android2021.utils.EasyLog
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody

/**
 * @Title: 实用的日志拦截器
 * @Package
 * @Description: Logging 拦截器
 * @author dhl
 * @date 2021 11 12
 * @version V1.0
 */


class LoggingInterceptor:Interceptor {

    companion object{
        const val TAG = "LoggingInterceptor"
    }
    override fun intercept(chain: Interceptor.Chain): Response {

        val timeStart = System.nanoTime()
        val request = chain.request()

        val buffer = okio.Buffer()
        request.body?.writeTo(buffer)
        val requestBodyStr = buffer.readUtf8()
        EasyLog.e(TAG,String.format("Sending request %s with params %s",request.url,requestBodyStr))
        val response = chain.proceed(request)

        val rspData = response.body?.string()?:"response body null"
        val mediaType = response.body?.contentType()
        val newBody = ResponseBody.create(mediaType,rspData)
        val endTime = System.nanoTime()
        EasyLog.e(TAG,String.format("Received response for %s in %.1fms%n%s",request.url,(endTime - timeStart)/1e6,rspData))

        return response.newBuilder().body(newBody).build()

    }
}