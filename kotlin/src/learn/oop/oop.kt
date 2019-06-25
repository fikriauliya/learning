package learn.oop

import dbllinear.dblLinearMainTest
import java.lang.AssertionError

class Empty
class Person constructor(firstName: String)
class Customer public constructor(name: String)
class Person2(firstName: String)
class Init(name: String) {
    val first = "first".also(::print)
    init { print("second") }
    val third = "third".also(::print)
    init { print("forth") }
}
class Person3(val firstName: String, var lastName: String)

//secondary constructor
class Person4(val name: String) {
    constructor(name: String, parent: Person4): this(name) {}
}
class DontCreateMe private constructor()

open class Base(p: Int) {
    constructor(p: Int, q: Int): this(p)
    open fun v() {}
    open fun v2() {}
    open val x: Int = 2
    fun nv() {}

//    inner class Baz {
//        fun g() {
//            super@Base.v()
//        }
//    }
}
open class Derived: Base {
    constructor(p: Int): super(p)
    constructor(p: Int, q: Int): super(p, q)
    override fun v() { super.v2() }
    override val x = 3
    final override fun v2() {}
    // override fun nv() {} -> error, since the parent method is not open
}

class Derived2(p: Int, q: Int, z: Int, override val x:Int = 3): Derived(p) {
    override fun v() {}
    // override fun v2() {} -> error, since the parent method is marked final
}

//interface can't store state
interface B {
    fun f() { print("f") } // open by default
    fun g()

    val prop: Int //abstract

}

open class A {
    open fun g() { print("A")}
}

class C: B, A() {
    override fun g() {
//        super<B>.g()
        super<A>.g()
    }

    override val prop = 2
}

abstract class D: B {
    override abstract fun f()
}

fun main() {
    val c = C()
    c.g()
}

class Address {
    var name: String = "Name"
    var firstName: String = "Pahlevi"
        private set(value) { field = value.toUpperCase() }
    private val lastName: String = ""
    val street : String = "Street"
    val age: Int? //no literal, must be assigned in constructor
    val fullName: String
        get() = "$this.name $this.lastName"

    constructor(name: String, street: String, age: Int?) {
        this.age = age
    }

    private var _table: Map<String, Int>? = null
    public val table: Map<String, Int>
        get() {
            if (_table == null) _table = HashMap()
            return _table ?: throw AssertionError("Set to null by another thread")
        }

    lateinit var num: Derived

}

const val PI = 3.14 //compile time const

//End of Chapter properties and fields https://kotlinlang.org/docs/reference/properties.html

//Data class
data class User(val name: String, val age: Int)
data class Pair<T>(val v1:T, val v2:T)
fun useDataClass() = {
    val (name, age) = User("Levi", 12)
}

//Sealed class


//Object
open class A2(x: Int) {
    public open val y: Int = x
}
val ab: A2 = object : A2(1) {
    override val y = 15
}

val adHoc = object {
    var x: Int = 0
    var y: Int = 0
}

class C1 {
    private fun foo() = object { val x: String = "X"}
    fun publicFoo() = object { val x: String = "x"}
    fun bar() {
        foo().x
        // publicFoo().x -> error, object return types' members can't be accessed
    }
}

object Singleton {
    fun f() {}
}
fun useSingleton() = Singleton.f()

class CompanionClass {
    companion object Factory {
        fun create(): CompanionClass = CompanionClass()
    }
//    The name can be omited
//    companion object {
//        fun create(): CompanionClass = CompanionClass()
//    }
}
fun useCompanionObject() = CompanionClass.create()
fun useCompanionObject2() = CompanionClass.Factory.create()

class MyList<E>(capacity: Int): ArrayList<E>(capacity) {
    fun test() {
        val t = mutableListOf<Int>(1, 3, 5)
    }
}