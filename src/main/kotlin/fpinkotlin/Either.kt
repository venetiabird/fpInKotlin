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
