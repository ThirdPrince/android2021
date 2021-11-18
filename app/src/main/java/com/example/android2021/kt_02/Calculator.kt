package com.example.android2021.kt_02

import java.lang.Exception
import kotlin.system.exitProcess

/**
 * @Title: Calculator
 * @Package
 * @Description: 四则运算表达式计算器
 * @author dhl
 * @date 2021 1102
 * @version V1.0
 */

fun main() {
    while (true) {
        println("====请输入你的表达式=====")
        val input = readLine()
        try {
            input?.let {
                val result = calculate(it)
                println("$it = $result")
                println("是否继续y/n")
                val cmd = readLine()
                cmd.let {
                    if (cmd.equals("n")) {
                        exitProcess(-1)
                    }
                }

            }
        } catch (ex: Exception) {
            ex.toString()
        }
    }
}

fun calculate(input: String): String {
    // input === 1+1
    // input = 1-10
    // input = 1/10
    // input = 1*11
    return if (input.contains("+")) {
        val nums = input.trim().split("+")
        operate(nums[0].toDouble(), nums[1].toDouble(), "+").toString()
    } else if (input.contains("-")) {
        val nums = input.trim().split("-")
        operate(nums[0].toDouble(), nums[1].toDouble(), "-").toString()
    } else if (input.contains("*")) {
        val nums = input.trim().split("*")
        operate(nums[0].toDouble(), nums[1].toDouble(), "*").toString()
    } else if (input.contains("/")) {
        val nums = input.trim().split("/")
        operate(nums[0].toDouble(), nums[1].toDouble(), "/").toString()
    } else {
        "error,你输入的有误"
    }
}

fun operate(num1: Double, num2: Double, operate: String): Double {
    return when (operate) {
        "+" -> num1 + num2
        "-" -> num1 - num2
        "/" -> num1 / num2
        "*" -> num1 * num2
        else -> 0.0
    }
}