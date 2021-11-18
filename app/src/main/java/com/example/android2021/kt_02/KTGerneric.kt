package com.example.android2021.kt_02

import org.json.JSONObject

/**
 * @Title:
 * @Package
 * @Description: 泛型接口学习
 * @author dhl
 * @date 2021 1101
 * @version V1.0
 */
fun main() {

    val drinkApple = DrinkApple()
    drinkApple.drink("apple")
    //泛型类
    val blueColor = BlueColor("blue")
    blueColor.printColor()

    val json = fromJson("{}", String::class.java)
    println("json = $json")

    //泛型约束
    fromJson2<JSONObject>("{}",JSONObject::class.java)
    fromJson3<User>("{}",User::class.java)

}

interface Drink<T> {
    fun drink(t: T)
}

class DrinkApple : Drink<String> {
    override fun drink(t: String) {
        println("drink = $t")
    }

}

abstract class Color<T>(val t: T) {

    abstract fun printColor()
}

class BlueColor(private val color: String) : Color<String>(color) {

    override fun printColor() {

        println("printColor =${color}")
    }

}

//泛型方法
fun <T> fromJson(json: String, tClass: Class<T>): T? {

    return tClass.newInstance()
}

//泛型约束,所传递的类型T 必须满足JSObject 的子类，或JSONObject类


fun <T :JSONObject> fromJson2(json:String,tClass:Class<T>):T?{
    return tClass.newInstance()
}

// 泛型类型限定-2
// 所传递的类型 必须同时满足where 子句的所有条件
// 在下面的示例中，类型T 必须实现了 User,也实现了Comparable
fun <T> fromJson3(json:String,tClass: Class<T>):T? where T :JSONObject,T:Comparable<T>{
    return tClass.newInstance()
}
class User:JSONObject(),Comparable<User>{
    override fun compareTo(other: User): Int {
      return 0
    }

}
open class Animal

class DogAnimal:Animal()

class CatAnimal:Animal()

fun animalFuns(){
    // 传入的泛型参数可以是Animal及 Animal 的子类，DogAnimal CatAnimal
    //1,使用处使用out 关键字声明 泛型上限
    val animalList :ArrayList<out Animal> = ArrayList<DogAnimal>()
    val animalList2:ArrayList<Animal> = ArrayList<DogAnimal>()
    // 2，使用in 关键字声明，约定泛型的下限
    val animalList3:InArrayList<DogAnimal> = InArrayList<Animal>()

}

// 在定义处使用out 关键字声明，允许传入的泛型参数可以是T 以及T的子类
class ArrayList<out T>{

}

class InArrayList<in T>{

}