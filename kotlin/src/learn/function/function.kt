package learn.function

fun double(x: Int): Int = 2 * x
fun foo(bar: Int = 0, qux: () -> Unit) {
    println(bar)
    qux()
}
fun goo(vararg strings: String) {}

fun main() {
    foo(2, qux = { println("end") })
    foo(2, { -> println("end") })
    foo(2) { println("end") }

    goo(*arrayOf("hello", "world"))

    val add = Fraction(2, 3) add Fraction(3 , 4)
}

class Fraction(val nom: Int, val denom: Int) {
    infix fun add(f: Fraction): Fraction {
        return Fraction((nom + f.nom), (denom + f.denom))
    }
    fun negate(): Fraction = Fraction(-nom, denom)
    infix fun substract(f: Fraction): Fraction = this add f.negate()
}

fun factorial(n: Int): Int {
    tailrec fun fac(accum: Int, n: Int): Int {
        if (n == 0) return accum
        return fac(accum * n, n - 1)
    }
    return fac(1, n)
}

class IntTransformer: (Int) -> Int {
    override operator fun invoke(x: Int): Int = x + 1
}
val intFunction: (Int) -> Int = IntTransformer()

fun lambda() {
    val items = listOf(1,2,3)
    val res = items.fold(0, { acc: Int, i: Int -> acc + i })
    val res3 = items.fold(0) { acc: Int, i: Int -> acc + i }
    val res1 = items.fold(0, fun(acc: Int, i: Int): Int{ return acc + i })
    val res2 = items.fold(0, fun(acc: Int, i: Int) = acc + i )
}