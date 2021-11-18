package com.example.android2021.kt04

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @Title: $
 * @Package
 * @Description: gson 学习
 * @author dhl
 * @date 2021 11 12
 * @version V1.0
 */

fun main(){
    //将json 转换为对象
    val json ="{\"uid\":\"00001\",\"userName\":\"Freeman\",\"telNumber\":\"13000000000\"}"
    val gson = Gson()
    val account = gson.fromJson(json,Account::class.java)
    println(account.toString())
    //对象转化为 gson
    val accountJson = gson.toJson(account)
    println(accountJson)

    val jsonArray= "[{\"uid\":\"00001\",\"userName\":\"Freeman\",\"telNumber\":\"13000000000\"}]"

    val accountList:List<Account> = gson.fromJson(jsonArray, object :TypeToken<List<Account>>(){}.type)
    println(accountList.toString())

    println(gson.toJson(accountList))



}

class Account{
    var uid:String = ""
    var userName:String = "Freeman"
    var password:String = "password"
    var telNumber:String = "1300000000"
    override fun toString(): String {
        return "Account(uid='$uid', userName='$userName', password='$password', telNumber='$telNumber')"
    }

}