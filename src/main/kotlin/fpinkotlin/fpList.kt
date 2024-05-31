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
            println("head ${a}, tail ${b}")
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
    }
}

// 3.1
// fun <A> Lizt<A>.tail(): Lizt<A> =
//     when (val lizt = this) {
//         is Nil -> Nil
//         is Cons -> lizt.tail
//     }

object Nil : Lizt<Nothing>()

data class Cons<out A>(val head: A, val tail: Lizt<A>) : Lizt<A>()
