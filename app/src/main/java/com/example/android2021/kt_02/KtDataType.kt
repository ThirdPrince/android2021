package com.example.android2021.kt_02

/**
 * @Title: kotlin study
 * @Package
 * @Description: 基本数据类型
 * @author  dhl
 * @date 2021 10.14
 * @version V1.0
 */

fun main() {
    /**
     *   1,如何声明一个基本数据类型，有哪些方式?
     *   基本数据类型的整型默认数据类型均为 Int 如果超过了Int 的取值范围，则会推断为Long
     *
     */
    // 编译器会根据赋值【100】推断变量number 数据类型为Int
    val number = 100
    // 虽然没有明确指定变量bigNumber的数据类型，但是编译器根据赋值发现【800000000】已经超过了Int 的最大值
    val bigNumber = 800000000

    // 在赋值的数字后面加上L,会自动推断为Long类型

    val longNum = 20L


    //在变量的后面+ : +数据类型

    val byteNumber: Byte = 1

    //浮点类型

    val doubleNumber = 3.1415928888

    val floatNumber = 3.1415928888f //尾部加f 或者F表示这是一个Float类型的浮点数

    println("floatNumber:$floatNumber")
    println("doubleNumber:$doubleNumber")

    //字符类型
    val char: Char = '0'

    //布尔类型,使用boolean 声明类型，只有两种true / false
    val isVisible: Boolean = false

    val isVisible2 = true

    //字符串类型

    val str: String = "1234567890"

    val strNumber2: Char = str[0]

    //字符串模板 以$开始
    println("strNumber2 = $strNumber2")

    println("I am " + 10 + "years old!")
    //字符转义
    val helloWorld = "hello,world!\n"
    print("$helloWorld how are you ")

    val helloWorld3 = "{\"key\",\"value\"}"
    println("helloWorld3 = $helloWorld3")

    //
    val helloWorld4 = """
        | Tell me and i forget
        | Teach me and i remember
        |{"key1":"value1"}
         
    """.trimIndent()

    println(helloWorld4)

    // 数据类型间的强制转换
    val number100: Int = 100

    println("转换成string:${number100.toLong()}")

    //数据类型的加减乘除
    // 在计算机里面整数除以整数，等到结果还是整数，小数点会被抹掉

    val numberInt: Int = 3 / 2
    println("numberInt = $numberInt") //输出1

    val floatNumber2: Double = 3 / 2.toDouble()
    println("floatNumber2= $floatNumber2")//输出1.5

    println("乘法：${numberInt * floatNumber2}")

    // 取余
    println("取余：=${3 % 2}")

    // 位运算
    val vip = true
    val admin = false

    // 与操作，要求两个都满足，结果才会为true
    val result = vip.and(admin)

    // 或操作，只要有个条件是true 结果才会为true
    val result2 = vip.or(admin)

    //无符号右移
    val result3 = 8 ushr (2)

    println("result3 = $result3")


}