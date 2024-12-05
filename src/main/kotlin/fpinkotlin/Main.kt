package fpinkotlin

import fpinkotlin.Option.Companion.flatMap
import fpinkotlin.Option.Companion.getOrElse
import fpinkotlin.Option.Companion.map
import fpinkotlin.Option.Companion.orElse

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
    println(Lizt.doubleToString(Lizt.of(1.0, 2.0, 3.0)))
    println(Lizt.map(words) { word -> "$word?" })
    println(Lizt.map(ints) { num -> num + 10 })
    println(Lizt.filter(ints) { num -> num % 2 == 0 })
    println(Lizt.flatMap(Lizt.of(1, 2, 3)) { i -> Lizt.of(i, i) })
    println(Lizt.filterUsingFlatmap(ints) { num -> num % 2 == 0 })
    println(Lizt.zipAndAdd(Lizt.of(1, 4, 7, 2, 6), ints))
    println(Lizt.zipWith(Lizt.of(1, 4, 7, 2, 6), ints) {x, y -> x-y })
    println(Lizt.zipWith(Lizt.of("A", "B", "C", "D", "D"), ints) {x, y -> x+y })
    println(Lizt.zipWith(Lizt.of("A", "B", "C", "D", "D"), ints) {x, y -> "$x -> $y" })
    println(words.slice())
    println(words.slice(2))
    println(words.equals(Lizt.of("apple", "banana", "strawberry", "peach")))
    println(Lizt.hasSubsequence(words, Lizt.of("banana", "peach")))
    println(Lizt.hasSubsequence(ints, Lizt.of(7, 2, 6, 8)))
    println(Lizt.hasSubsequence(ints, Lizt.of()))

    val tree =
        Branch(
            Branch( // 1
                Leaf(3),
                Leaf(302),
            ),
            Branch(
                Branch(
                    Leaf(-1),
                    Branch(
                        Branch(
                            Leaf(11),
                            Leaf(90)),
                        Leaf(101),
                    ),
                ),
                Leaf(99),
            ),
    )


    println(Tree.fold(
        tree,
        { x: Int -> x.toString() },
        { b1, b2 -> b1 + b2}
    ))
    println(tree.size())
    println(Tree.sizeF(tree))

    println(Tree.maximum(tree))
    println(Tree.maximumF(tree))

    println(Tree.depth(tree))
    println(Tree.depthF(tree))

    println(tree.map { x -> x + 1 })
    println(Tree.mapF(tree) { x -> x + 1 })

    val someValue: Option<Int> = Some(5)
    val noneValue: Option<Int> = None

    println(someValue.map { double(it) })
//    println(noneValue.map { double() })
    println(someValue.flatMap { v -> Some(v + 1) })
    println(noneValue.getOrElse { 99 })
    println(someValue.getOrElse { 99 })
    println(someValue.orElse { Some(3) }) // Some(5)
    println(someValue.orElse { None }) // Some(5)
    println(noneValue.orElse { Some(3) }) // Some(3)
    println(noneValue.orElse { None }) // None
}

fun double(x: Int): Int = x * 2

