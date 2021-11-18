package com.example.android2021.kt_02

import android.os.Build
import androidx.annotation.RequiresApi

/**
 * @Title: 集合
 * @Package $
 * @Description: 集合
 * @author dhl
 * @date 2021 - 1023
 * @version V1.0
 */

/**
 *  kotlin 集合
 *  List ：是一个有序列表，可通过索引(下标)访问元素，元素可以在list中出现多次，元素可以重复
 *  Set ：是元素唯一的集合，一般来说，Set中元素的顺序并不重要，无序集合
 *  Map：（字典） 是一组键值对，键是唯一的，每个键刚好映射到一个值，值可以重复。
 *
 */

fun main() {

    // 列表的创建方式 -- 可变列表
    val arrayString = mutableListOf<String>()
    arrayString.add("1")
    arrayString.add("2")
    arrayString.add("2")
    arrayString.add(3, "3")


    val arrayList2 = mutableListOf<String>("1", "2", "3")
    arrayList2.add("4")

    //不可变集合
    val arrayInt = listOf<Int>(1, 2, 3)

    //map 字典的创建 -- 可变字典
    // 字典 是一组键值对，每个键值都刚好映射一个值，值可以重复
    //  val array  = mapOf(Pair("key","value"))

    val arrayMap = mutableMapOf<String, String>()
    arrayMap["1"] = "1"
    arrayMap["2"] = "2"
    arrayMap["3"] = "3"
    arrayMap["3"] = "4" // 会覆盖上面的记录


    // map 字典的创建--使用Pair 指定集合中初始化的元素
    val arrayMap2 = mutableMapOf(Pair("key1", "value"))

    //set集合创建-- 可变集合，元素唯一
    val set = mutableSetOf<Int>()
    set.add(1)
    set.add(2)
    set.add(3)
    set.add(3)
//    set.forEach {
//        println("set = $it")
//    }
    val set2 = mutableSetOf<Int>(1, 2, 3, 4)
    set2.add(1)
    set2.add(2)
    set2.forEach {
        println("set2 = $it")
    }
    val set3 = setOf(1, 2, 3)

    val array = mutableListOf("1", "2", "3")
    println("array isEmpty()= ${array.isEmpty()}")
    println("array contains 2 = ${array.contains("6")}")
    println("array get = ${array[0]}")
    println("indexOf = ${array.indexOf("2")}")
    val iterator = array.iterator()
    iterator.forEach {
        println("array iterator = $it")
    }
    array.clear()
    //array[0] = "0"
    array.add("2")
    val numbers = mutableListOf(1, 2, 3, 4)
    numbers.reverse()
    println("numbers = $numbers")

    numbers.shuffle()

    println("numbers = $numbers")

}