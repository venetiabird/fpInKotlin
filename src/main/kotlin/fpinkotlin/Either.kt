package fpinkotlin

sealed class Either<out E, out A> {
    companion object {
        fun mean(xs: List<Double>): Either<String, Double> =
            if (xs.isEmpty())
                Left("mean of empty list!")
            else Right(xs.sum() / xs.size)

        fun <A> catches(a: () -> A): Either<Exception, A> =
            try {
                Right(a())
            } catch (e: Exception) {
                Left(e)
            }

        fun <E, A, B, C> map2(
            ae: Either<E, A>,
            be: Either<E, B>,
            f: (A, B) -> C
        ): Either<E, C> =
//            when(ae) {
//                is Left -> ae
//                is Right -> be.map { b -> f(ae.value, b) }
//            }
            ae.flatMap { a ->
                be.map { b ->
                    f(a, b)}
            }
//            when(ae) {
//                is Left -> ae
//                is Right -> when(be) {
//                    is Left -> be
//                    is Right -> Right(f(ae.value, be.value))
//                }
//            }

        fun <A, B, E> traverse(
            xa: Lizt<A>,
            f: (A) -> Either<E, B>
        ): Either<E, Lizt<B>> {
            val result = Lizt.foldLeft(xa, Right(Nil) as Either<E, Lizt<B>>) { acc, a ->
                acc.flatMap { list ->
                    val x = f(a)
                    x.map { b -> Lizt.appendRight(list, b) }
                }

            }

            return result
        }
    }
}

data class Left<out E>(val value: E) : Either<E, Nothing>()
data class Right<out A>(val value: A) : Either<Nothing, A>()

// Ex 4.6
fun <E, A, B> Either<E, A>.map(f: (A) -> B): Either<E, B> =
    when (this) {
        is Left -> this
        is Right -> Right(f(this.value))
    }

fun <E, A, B> Either<E, A>.flatMap(f: (A) -> Either<E, B>): Either<E, B> =
    when (this) {
        is Left -> this
        is Right -> f(this.value)
    }

fun <E, A> Either<E, A>.orElse(f: () -> Either<E, A>): Either<E, A> =
     when (this) {
         is Left -> f()
         is Right -> this
     }

