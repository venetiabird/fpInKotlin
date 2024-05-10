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
                    else
                        doubles.head * product(doubles.tail)
            }

    }
}
object Nil : Lizt<Nothing>()
data class Cons<out A>(val head: A, val tail: Lizt<A>) : Lizt<A>()

