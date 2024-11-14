package fpinkotlin

sealed class Option<out A>
data class Some<out A>(val get: A) : Option<A>()
object None : Option<Nothing>()

fun <A, B> Option<A>.map(f: (A) -> B): Option<B> =
