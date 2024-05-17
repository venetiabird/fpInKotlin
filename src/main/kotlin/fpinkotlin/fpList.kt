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
