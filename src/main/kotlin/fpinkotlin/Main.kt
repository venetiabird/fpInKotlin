package fpinkotlin

fun main() {
    val words = Lizt.of("apple", "banana", "strawberry", "peach")
    val ints = Lizt.of(1, 4, 7, 2, 6, 8)
    println("Hello World!")
    println(Lizt.of(arrayOf(1, 2, 3)))
    println(Lizt.getTail(words))
    println(Lizt.setHead(words, "Guava"))
    println(Lizt.product(Lizt.of(1.0, 2.0)))
    println(Lizt.drop(Lizt.of(1, 4, 7, 2, 6, 8), 3))
    println(Lizt.dropWhile(Lizt.of(1, 4, 7, 2, 6, 8)) { n -> n != 7 })
    println(Lizt.init(Lizt.of(1, 4, 7, 2, 6, 8)))
    println(Lizt.productZ(Lizt.of(6.0, 0.0, 7.1)))
    println(Lizt.foldRight(
        Cons(1, Cons(2, Cons(3, Nil))),
        Nil as Lizt<Int>
    ) { x, y -> Cons(x, y) })
    println(Lizt.lengthLeft(words))
    println(Lizt.sumLeft(ints))
    println(Lizt.productLeft(Lizt.of<Double>(1.0, 2.0, 3.0)))
    println(Lizt.reverse(Lizt.of<Double>(1.0, 2.0, 3.0)))
    println(Lizt.appendRight(Lizt.of<Double>(1.0, 2.0, 3.0), 4.0))
    println(Lizt.concat(Lizt.of<Double>(1.0, 2.0, 3.0), Lizt.of<Double>(4.0, 5.0)))
    println(Lizt.flatten(Lizt.of(Lizt.of<Double>(1.0, 2.0, 3.0), Lizt.of<Double>(4.0, 5.0, 6.0))))
    println(Lizt.transformOne(Lizt.of(1, 2, 3)))
}
