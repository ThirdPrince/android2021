package com.example.android2021.kt_02

/**
 * @Title: KtExtensions
 * @Package
 * @Description: 扩展函数练习
 * @author dhl
 * @date 2021 1102
 * @version V1.0
 */
fun main() {

    val str = Jump().doubleJump()
    println("str =$str")
    val list = mutableListOf<Int>(1,2,3,4)
    println(list)
    list.swap(0,2)
    println(list)

    val android = "android"
    val lastChar = android.lastChar
    println("lastChar = $lastChar")

    testApply()
}

class Jump{
    fun test(){}
}
//扩展方法的定义，就是在方法的前面加上前缀
fun Jump.doubleJump():String{
    return "doubleJump"
}

//泛型扩展方法
fun <T>MutableList<T>.swap(index:Int,index2:Int){
    val temp = this[index]
    this[index] = this[index2]
    this[index2] = temp
}
//扩展属性
val String.lastChar:Char get() = this[length-1]

// let 扩展函数，类后面加上？代表参数可能为空，使用的时候，注意判空
// let 是作用域函数
fun testLet(str:String){
    str.let{
        val str2 = "android"
        println(str + it)
    }

    //判空用法
    str?.let {
        println(it.length)
    }
}

// run 扩展函数
// 返回值为最后一行的值或者指定的return 的表达式，在run 函数中可以直接访问实例的公有属性和方法
fun testRun(jump:Jump):String{
    jump.run {
        test()
        println("11111")
        return "223"
    }
}

//
fun testApply(){
    mutableListOf<String>().apply {
        add("1111")
        add("222")
    }.run {
        for(s in this){
            println("s ==$s")
        }
    }
}

