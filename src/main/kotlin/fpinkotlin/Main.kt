package fpinkotlin

fun main() {
    val words = Lizt.of("apple", "banana", "strawberry")

    println("Hello World!")
    println(Lizt.of(arrayOf(1, 2, 3)))
    println(words.tail())
    println(Lizt.product(Lizt.of(1.0, 2.0)))
}
