package com.example.android2021.kt04

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * @Title: $
 * @Package $
 * @Description: Retrofit 学习
 * @author $
 * @date $
 * @version V1.0
 */
object HiRetrofit {


    const val IMOOC_BASE_URL = "https://www.songyubao.com/"

    private val client = OkHttpClient.Builder()// builder 构建者设计模式
        .connectTimeout(10, TimeUnit.SECONDS)// 连接超时时间
        .readTimeout(10, TimeUnit.SECONDS)//读取超时
        .writeTimeout(10, TimeUnit.SECONDS)// 写超时，也就是请求超时
        .addInterceptor(LoggingInterceptor())
        .build()
    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(IMOOC_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun<T> create(clazz: Class<T>):T{
     return retrofit.create(clazz)
    }
}

interface  ApiService{

    @GET(value = "user/query")
    fun queryUser(@Query(value = "userId",encoded = true) userId :String): Call<UserResponse>

    @GET(value = "static/book/assets/study.json")
    fun getStudy():Call<List<CourseItem>>
}