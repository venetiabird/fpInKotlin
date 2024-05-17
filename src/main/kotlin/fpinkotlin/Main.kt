package fpinkotlin

fun main() {
    val words = Lizt.of("apple", "banana", "strawberry")

    println("Hello World!")
    println(Lizt.of(arrayOf(1, 2, 3)))
    println(Lizt.getTail(words))
    println(Lizt.setHead(words, "Guava"))
    println(Lizt.product(Lizt.of(1.0, 2.0)))
    println(Lizt.drop(Lizt.of(1, 4, 7, 2, 6, 8), 3))
    println(Lizt.dropWhile(Lizt.of(1, 4, 7, 2, 6, 8)) { n -> n != 7 })
}
