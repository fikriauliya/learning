package learn.basic

import java.lang.IllegalStateException
import java.util.*;

fun sum(a: Int, b: Int): Int { return a + b }
fun sumShort(a: Int, b: Int) = a + b
fun printSum(a: Int, b: Int): Unit { println("sum of $a and $b is ${a + b}") }

fun variabels() {
    val a: Int = 1
    val b = 2
    val c: Int
    c = 4 // deferred
    var x = 5
    x += 5
}

fun conditional(a: Int, b: Int) {
    val max = if (a > b) { a } else { b }
    val maxShort = if (a > b) a else b
    val obj = null
    when (obj) {
        1 -> "One"
        "Hello" -> "Greeting"
        is Long -> "Long"
        else -> "Unknown"
    }
    val x = 1
    if (x in 1..100) println("within range")
    if (x !in 1..100) println("out of range")
}

fun parseInt(str: String): Int? {
    if (str.isEmpty()) return null
    return Integer.parseInt(str)
}

fun useNullablle() {
    val num = parseInt("hello")
    if (num != null) print(num + 1)
    if (num is Int) print(num + 1)
    if (num !is Int) print("NaN")
}

fun loop() {
    val items = listOf("apple", "orange")
    for (item in items) println(item)
    for (index in items.indices) println(items[index])
    for (x in 1..10 step 2) println(x)
    for (x in 1 downTo 0 step 1) println(x)
    var index = 0
    while (index < items.size) { println(items[index]); index+=1 }
}

fun lambda() {
    val fruits = listOf("banana", "apple")
    fruits
            .filter { it.startsWith("ba") }
            .map { i -> i.toUpperCase() }
}

fun func(a: Int = 0, b:String = "") { println(a) }

fun String.spaceToTab() = this.replace(" ", "\t") //extension method
object Resource { //singleton
    val name = "Name"
}

fun nullRelated() {
    val files = listOf("s", "ss")
    val size = files?.size
    val size2 = files?.size ?: 0

    val value = 2
    val res = value?.let { it + 2 }
}

fun exception() {
    val res = try {
        throw ArithmeticException()
    } catch (e: ArithmeticException) {
        throw IllegalStateException(e)
    }
}

data class Customer(val name: String, val email: String)

class Turtle {
    fun penDown() {}
    fun penUp() {}
}

fun withKeyword() {
    val myTurtle = Turtle()
    with(myTurtle) {
        penDown()
        penUp()
    }
}

fun swap() {
    var a = 1
    var b = 2
    a = b.also { b = a }
}

fun type() {
    val l = 123L
    val hex  = 0x0F
    val bin = 0b001
    val f = 123.3f

    val a: Int = 100
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    println(boxedA === anotherBoxedA) //identity -> false
    println(boxedA == anotherBoxedA) //equality -> true
    val i: Int = l.toInt()
    val c = 'c'

    val arr = arrayOfNulls<Int>(3)
    val arr2 = Array(5, { it * it })
    val intArr = intArrayOf(1, 2, 3)

    val s= "Hello\n"
    val text = """
        this is
        long text${2*3}
    """.trimIndent()
    val text2 = """
        |this is
        |long text
    """.trimMargin()

}
fun flow() {
    val x = 0
    val s = "0"
    when (x) {
        0, 1 -> print("x == 1 or 0")
        parseInt(s) -> print("== s")
        in 1..10 -> print("in range")
        else -> print("Others")
    }
    when { //no params
        x % 2 == 0 -> print("Even")
    }
    for (i in 1..3) print(i)
    for (i in arrayOf(1,2,3).indices) print(i)
    for ((i, value) in arrayOf(1,2,3).withIndex()) print("$i, $value")
    var i = 0
    while (i < 0) i--
    do { i ++ } while (i < 10)
}

fun returnsJumps() {
    loop@ for (i in 1..100) {
        for (j in 1..100) {
            break@loop
        }
    }

    listOf(1,2,3).forEach { return } //return from func
    //all equals:
    listOf(1,2,3).forEach lit@ { return@lit }
    listOf(1,2,3).forEach { return@forEach }
    listOf(1,2,3).forEach(fun(value) {return})

    run loop@{ listOf(1,2,3).forEach{return@loop} }
}