package com.example.android2021.kt_02

/**
 * @Title: lambda
 * @Package $
 * @Description: kotlin 方法
 * @author $
 * @date 2021 10 25
 * @version V1.0
 */

fun main(){

    //普通类的成员方法声明与调用
    // 需要构件出实例对象，才能访问成员方法
    // 实例对象的构建只需要在类名后面加上()
    // new Person() java
    Person().test()
    val num = NumUtil.double(3)
    println("num = $num")
    Person.test2()
    //具名参数
    read(start = 4)

    read2(start = 2){
        println("this is read 2")
    }

    read3(1,2,action = {
        "括号内。。。。"
    })
    read3{
        "kkkkk"
    }

    val appendStr1 = append('1','2','3')
    println("appendStr = $appendStr1")
    val world = charArrayOf('w','o','r','l','d')
    val appendStr2 = append('h','e','l','l','o',*world,num = 3)
    println("appendStr = $appendStr2")

    val numbers = arrayOf(1,2,3,4)
    transform(numbers, action = {index, element ->
        index + element //闭包中最后一行是表达式，计算后的结果，就是action函数的返回值
    })
    transform(numbers){ index, element ->
        index * element
    }
     numbers.forEach {
         println("numbers = $it")
     }

    val list = arrayListOf(1,2,3,5,6)
    // 具名参数
    list.forEachIndexed(action =  {index:Int,element:Int ->
        println("forEachIndexed index = $index :: element = $element ")
    })

    list.forEachIndexed { index, element ->
        println("forEachIndexed2 index = $index :: element = $element ")
    }
}

class Person{
    fun test(){
        println("成员方法")
    }
    companion object{
        fun test2(){
            println("伴生类成员方法")
        }
    }
}


/**
 * 静态类
 */
object NumUtil{
    fun double(num:Int):Int{
        return num *2
    }
}

/**
 * 默认参数
 *
 */
fun read(offset:Int = 0,start:Int ){
    println("read offset = $offset::start = $start")
}

/**
 * 方法参数
 */
fun read2(offset:Int = 0,start:Int,action:() -> Unit){
    action()
    //println("read offset = $offset::start = $start")
}
fun read3(offset:Int = 0,start:Int= 0,action:() -> String){
    val result = action()
    println("result = $result")
}

/**
 * 可变数量参数,最后一个参数要用具名参数
 */
fun append(vararg str:Char,num:Int = 0):String{
    val result = StringBuffer()
    for(char:Char in str){
        result.append(char)
    }
    return result.toString()
}
/**
 * lambda
 * array 要求传入一个数组，元素类型为Int 的整数类型
 */

fun transform(array:Array<Int>,action:(index:Int,element:Int) ->Int){
    for(index in array.indices) {
        val newValue = action(index, array[index])
        array[index] = newValue
    }
}
