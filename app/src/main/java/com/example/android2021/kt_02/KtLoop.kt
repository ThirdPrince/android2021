package com.example.android2021.kt_02

/**
 * @Title: KtLoop$
 * @Package $
 * @Description: $(用一句话描述)
 * @author dhl$
 * @date 2021 1101$
 * @version V1.0
 */

fun main() {
    val items = listOf("java", "kotlin", "android")
    // for in
    for (item in items) {
        println("item = $item")
    }
    // forEach
    items.forEachIndexed { index, item ->
        println("forEachIndexed:$index,$item")
    }

    // forEach
    items.forEach {
        println("item = $it")
    }
    // while
    var index = 0
    while (index < items.size) {
        println("the $index element is ${items[index++]}")
    }
    //doWhile
    index = 0
    do {
        println("the $index element is ${items[index++]}")
    } while (index < items.size)

    //迭代器区间
    for (i in 1..10) {
        println("in  i==$i")
    }
    // fo in-until

    for (i in 1 until 10) {
        println("until i == $i")
    }
    //downATo 是指 10...1
    for (i in 10 downTo 1) {
        println("downTo i == $i")
    }

    for (i in 10 downTo 1 step 2) {
        println("down to step $i")
    }

    //break
    for (i in 1..100) {
        if (i == 50) {
            break
        }
        println("break i ==$i")
    }

    // continue
    for (i in 1..100) {
        if (i < 80) {
            continue
        }
        println("continue i =$i")

    }


}