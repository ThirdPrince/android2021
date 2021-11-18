package com.example.android2021.kt04

import android.os.Environment
import android.util.Log
import com.example.android2021.utils.EasyLog
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.File

/**
 * @Title:
 * @Package
 * @Description: OkHttp 的封装
 * @author dhl
 * @date 2021 11 12
 * @version V1.0
 */

const val TAG = "HiOKHttp"

object HiOKHttp {

     const val BASE_URL = "http://123.56.232.18:8080/serverdemo/"

    private val client = OkHttpClient.Builder()// builder 构建者设计模式
        .connectTimeout(10, TimeUnit.SECONDS)// 连接超时时间
        .readTimeout(10, TimeUnit.SECONDS)//读取超时
        .writeTimeout(10, TimeUnit.SECONDS)// 写超时，也就是请求超时
        .addInterceptor(LoggingInterceptor())
        .build()


    // Android 分为主线程，和子线程
    // 主线程就是APP 已启动之后，咱们Android framework 层会启动一个线程，主线程（UI线程）
    // 子线程 new Thread().start()

    fun get(url: String) {
        val request = Request.Builder().url(BASE_URL + url).build()
        val call = client.newCall(request)
        thread {
            val response = call.execute()
            println("response = ${response.body?.string()}")
        }

    }

    fun getByAsyn(url: String) {
        val request = Request.Builder().url(BASE_URL + url).build()
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "onFailure =${e.toString()}")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(TAG, "onFailure =${response.body?.string()}")
            }

        })
    }

    /**
     * post body
     * 表单提交
     */
    fun postByFormBody(url: String, map: Map<String, Int>) {

        val builder = FormBody.Builder()
        map.forEach {
            builder.add(it.key, it.value.toString())
        }
        val requestBody = builder.build()
        val request = Request.Builder().url(BASE_URL + url).post(requestBody).build()
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "onFailure =${e.toString()}")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(TAG, "onFailure =${response.body?.string()}")
            }

        })

    }

    /**
     * post 异步请求【多表单文件上传】
     * MultipartBody
     */

    fun postAsynMultipart(url: String) {

        val multiBody = MultipartBody.Builder()
            .addFormDataPart("key", "value")
            .addFormDataPart("key1", "value")
            .addFormDataPart(
                "file", "file.png", RequestBody.create(
                    "application/octet-stream".toMediaType(),
                    File(Environment.DIRECTORY_PICTURES, "1.png")
                )
            )
            .build()
        val request = Request.Builder().url(url).post(multiBody).build()
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {

            }

        })
    }


    /**
     * 提交字符串,可以是纯文本的字符串，也可以是JSON 字符串
     */
    fun postJson(url: String) {
        val jsonObj = JSONObject()
        jsonObj.put("useId", "1600932269")
        val requestBody =
            RequestBody.create("application/json;charset=utf-8".toMediaType(), jsonObj.toString())
        val request = Request.Builder().url(BASE_URL).post(requestBody).build()
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                EasyLog.e(TAG, e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { EasyLog.e(TAG, it) }
            }

        })

    }

    /**
     * 提交text
     */
    fun postText() {
        val text = "{username:admin, password:admin}"
        val requestBody = RequestBody.create("text/plain;charset=utf-8".toMediaType(), text)
        val request = Request.Builder().url(BASE_URL).post(requestBody).build()
        val call = client.newCall(request = request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                EasyLog.e(TAG, e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { EasyLog.e(TAG, it) }
            }

        })

    }


}