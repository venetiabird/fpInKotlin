package fpinkotlin

fun main() {
    println("Hello World!")
    println(Lizt.of(arrayOf(1, 2, 3)))
    println(Lizt.of("apple", "banana").tail())

    println(Lizt.product(Lizt.of(1.0, 2.0)))
}
