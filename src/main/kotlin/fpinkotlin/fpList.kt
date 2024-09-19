package fpinkotlin


sealed class Lizt<out A> {
    companion object {
        fun <A> of(vararg aa: A): Lizt<A> {
            val tail = aa.sliceArray(1 until aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }

        fun sum(int: Lizt<Int>): Int =
            when (int) {
                is Nil -> 0
                is Cons -> int.head + sum(int.tail)
            }
        fun product(doubles: Lizt<Double>): Double =
            when (doubles) {
                is Nil -> 1.0
                is Cons ->
                    if (doubles.head == 0.0) 0.0
                    else doubles.head * product(doubles.tail)
            }

        fun <A> getTail(l: Lizt<A>): Lizt<A> =
            when (l) {
                is Nil -> Nil
                is Cons -> l.tail
            }

        // 3.2
        fun <A> setHead(xs: Lizt<A>, x: A): Lizt<A> =
            when (xs) {
                is Nil -> Nil
                is Cons -> Cons(x, xs.tail)
            }

        // 3.3
        fun <A> drop(l: Lizt<A>, n: Int): Lizt<A> =
            when (l) {
                is Nil -> Nil
                is Cons -> if (n == 0) l else drop(l.tail, n - 1)
            }

        // 3.4
        fun <A> dropWhile(l: Lizt<A>, f: (A) -> Boolean): Lizt<A> =
            when (l) {
                is Nil -> Nil
                is Cons -> if (!f(l.head)) l else dropWhile(l.tail, f)
            }

        // 3.5
        fun <A>init(l: Lizt<A>) : Lizt<A> =
            when (l) {
                is Nil -> l
                is Cons -> if (l.tail is Nil) Nil else Cons(l.head, init(l.tail))
            }

        fun <A,B> foldRight(xs: Lizt<A>, z: B, f: (A, B) -> B): B =
            when (xs) {
                is Nil -> z
                is Cons -> f(xs.head, foldRight(xs.tail, z, f))
            }

        // 3.6
        fun productZ(dbs: Lizt<Double>): Double = foldRight(dbs, 1.0) { a, b ->
            println("head ${a}, tail $b")
            a * b
        }
        fun <A> empty(): Lizt<A> = Nil

        fun <A> length(xs: Lizt<A>): Int =
            foldRight(xs, 0) { _, b -> b + 1}

        tailrec fun <A, B> foldLeft(xs: Lizt<A>, z: B, f: (B, A) -> B): B =
            when (xs) {
                is Nil -> z
                is Cons -> foldLeft(xs.tail, f(z, xs.head), f)
            }

        // 3.10
        fun sumLeft(xs: Lizt<Int>): Int =
            foldLeft(xs, 0) { b, a -> a + b }

        fun productLeft(xs: Lizt<Double>): Double =
            foldLeft(xs, 1.0) { b, a -> a * b }

        fun <A> lengthLeft(xs: Lizt<A>): Int =
            foldLeft(xs, 0) { b, _ -> b + 1 }

        // 3.11
        // List [[1, 2], 3]
        // xs: List [1, 2] -> List 2, 3, nil, f(nil, 1)
        // z: List[1, nil]
        // z: List[2, 1, nil]
        fun <A>reverse(xs: Lizt<A>): Lizt<A> =
            foldLeft(xs, empty()) { b, a ->  Cons(a, b) }

        // 3.13
        fun <A>appendRight(xs: Lizt<A>, x: A): Lizt<A> =
            foldRight(xs, Cons(x, Nil)) { a, b -> Cons(a, b)}

        fun <A>concat(xs: Lizt<A>, zs: Lizt<A>): Lizt<A> =
            foldRight(xs, zs) { a, b -> Cons(a, b)}


        // tried this and it's not possible!
        // fun <A>appendLeft(xs: Lizt<A>, x: A): Lizt<A> =
        //     foldLeft(xs,  empty()) { b, a -> concat(b, Cons(a, Nil)) }

        // 3.14
        fun <A>flatten(xsLists: Lizt<Lizt<A>>): Lizt<A> =
            foldRight(xsLists, empty()) { a, b -> concat(a, b)}

        // 3.15
        fun transformOne(xs: Lizt<Int>): Lizt<Int> =
            foldRight(xs, empty()) { a, b -> Cons(a + 1, b)}

        // 3.16
        fun doubleToString(ds: Lizt<Double>): Lizt<String> =
            foldRight(ds, empty()) { a, b -> Cons("$a!!", b) }

        // 3.17
        fun <A, B> map(xs: Lizt<A>, f: (A) -> B): Lizt<B> =
            foldRight(xs, empty()) { a, b -> Cons(f(a), b) }

        // 3.18
        fun <A> filter(xs: Lizt<A>, f: (A) -> Boolean): Lizt<A> =
            foldRight(xs, empty()) { a, b ->
                when (f(a)) {
                    true -> Cons(a, b)
                    false -> b
                }
            }

        // 3.19
        fun <A, B> flatMap(xa: Lizt<A>, f: (A) -> Lizt<B>): Lizt<B> =
            foldRight(xa, empty()) { a, b ->
                val result: Lizt<B> = f(a)
                concat(result, b)
            }

        // 3.20
        fun <A> filterUsingFlatmap(xs: Lizt<A>, f: (A) -> Boolean): Lizt<A> =
            flatMap(xs) { x ->
                when (f(x)) {
                    true -> Cons(x, empty())
                    false -> empty()
                }}

        // 3.21
        fun zipAndAdd(xs1: Lizt<Int>, xs2: Lizt<Int>): Lizt<Int> =
            when (xs1) {
                is Nil -> empty()
                is Cons -> when (xs2) {
                    is Nil -> empty()
                    is Cons -> Cons(xs1.head + xs2.head, zipAndAdd(xs1.tail, xs2.tail))
                }
            }
//        fun zipAndAdd(xs1: Lizt<Int>, xs2: Lizt<Int>): Lizt<Int> {
//            val pair = (Pair(xs1, xs2))
//            when (pair) {
//                pair.first -> empty()
//                pair.second -> empty()
//                else
//                is Cons -> when (xs2) {
//                    is Nil -> empty()
//                    is Cons -> Cons(xs1.head + xs2.head, zipAndAdd(xs1.tail, xs2.tail))
//                }
//            }
//        }
        // [[1, 2, 3], [4, 5, 6]]

        // 3.22
        fun <A, B, C> zipWith(xs1: Lizt<A>, xs2: Lizt<B>, f: (A, B) -> C): Lizt<C> =
            when (xs1) {
                is Nil -> empty()
                is Cons -> when (xs2) {
                    is Nil -> empty()
                    is Cons -> Cons(f(xs1.head, xs2.head), zipWith(xs1.tail, xs2.tail, f))
                }
            }

        tailrec fun <A> hasSubsequence(xs: Lizt<A>, sub: Lizt<A>): Boolean {
            when (xs) {
                is Nil -> return false
                is Cons -> {
                    val subsize  = length(sub) // 2
                    val xsSub = xs.slice(subsize) // first 2 items in the larger list
                    if (xsSub == sub) {
                        return true;
                    }
                    return hasSubsequence(xs.tail, sub)
                }
            }
        }
    }

}

fun <A>Lizt<A>.slice(end: Int = Lizt.length(this)) : Lizt<A> {
    return when(this) {
        is Cons -> {
            if (end == 0) {
                Nil
            } else {
                Cons(this.head, this.tail.slice(end-1))
            }
        }
        else -> {
            Nil
        }
    }
}

// 3.1
// fun <A> Lizt<A>.tail(): Lizt<A> =
//     when (val lizt = this) {
//         is Nil -> Nil
//         is Cons -> lizt.tail
//     }

object Nil : Lizt<Nothing>() {
    override fun toString(): String = ""
}

data class Cons<out A>(val head: A, val tail: Lizt<A>) : Lizt<A>() {
    override fun toString(): String = "${head}, ${tail}"
}
