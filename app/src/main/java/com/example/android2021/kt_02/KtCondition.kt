package com.example.android2021.kt_02

import java.lang.IllegalArgumentException

/**
 * @Title: KtCondition
 * @Package
 * @Description:
 * @author dhl
 * @date 2021 1026
 * @version V1.0
 */

fun main() {

    println(maxOf(3, 6))
    eval(4)
    val str = evalByWhen(5L)
    println("when = $str")
}

fun maxOf(a: Int, b: Int): Int {
    return if (a > b) {
        a
    } else {
        b
    }
}

fun eval(number: Number) {
    if (number is Int) {
        println("this is int number")
    } else if (number is Double) {
        println("this is double number")
    } else if (number is Float) {
        println("this is float number")
    } else if (number is Long) {
        println("this is long number")
    } else if (number is Byte) {
        println("this is byte number")
    } else {
        throw IllegalArgumentException("invalid args")
    }
}

/**
 * when 同样是带有返回值的
 * when 将他的参数与所有的分支条件顺序比较，直到某个分支满足条件
 */
fun evalByWhen(number: Number):String {
   return when (getValue()) {
        is Int -> "this is int number"

        is Long -> "this is long number"

        is Float -> "this is float number"

        is Double -> "this is double number"

        else -> "invalid num"
    }
}

fun getValue():Any{ //类似Java里的 object
    return 100
}