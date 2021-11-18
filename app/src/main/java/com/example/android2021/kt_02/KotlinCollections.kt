package com.example.android2021.kt_02

import org.json.JSONObject

/**
 * @Title: 数据容器 数组（-）
 * @Package $
 * @Description: 集合
 * @author $
 * @date $
 * @version V1.0
 */

fun main() {
    // kotlin 数组
    // 1,使用arrayOf 创建数组，必须指定数组的元素，可以是任意类型
    val arrayNumber = arrayOf(1, 2, 3, 4)
    // 集合中的元素可以是任何类型
    // kotlin any 等价于 Java
    val arrayObjects = arrayOf(1, true, "2", "JSONObject")

    //2,使用arrayOfNull 创建空数组
    val arrayOfNulls = arrayOfNulls<String>(5)

    arrayOfNulls[0] = "element1"
    arrayOfNulls[1] = "element2"
    arrayOfNulls[2] = "element3"
    arrayOfNulls[3] = "element4"
    arrayOfNulls[4] = null

    // 3，利用array 的构造函数，动态创建数组
    // 创建一个Array<String> 初始化为【"0","1","4","9","16"]
    //数组创建的时候，会循环5次，i 就是数组的下标
    // -> 右边表达式的结果 ，就是数组中当前下标的元素
    val asc = Array(5) { i -> (i * i).toString() }

    // 5,使用IntArray

    val intArray = IntArray(5)
    intArray[0] = 1

    // 创建一个长度为5的值全为100的IntArray[100,100,100,100,100]
    val intArr2 = IntArray(5) { 100 }

    // 注意这里it 是它索引下标值，所以这里创建一个长度为5的IntArray
    val intArr3 = IntArray(5) { it * 2 } //it lambda 表达式专有变量，这里指数组的下标


    //数组如何遍历
    for (item in intArr3) {
        println(item)
    }

    // 根据下标再取出对应位置的元素
    // for -in
    for (i in intArr3.indices) {
        println(i.toString() + "->" + intArr3[i])
    }
    // 同时遍历下标和元素
    for ((index, item) in intArr3.withIndex()) {
        println("$index ->$item")
    }

    // forEach
    intArr3.forEach {
        println("forEach = $it")
    }

    // forEach 增强版
    intArr3.forEachIndexed { index, item ->
        println("$index -> $item")
    }


}