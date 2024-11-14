package fpinkotlin

sealed class Option<out A> {
    companion object {
        fun <A, B> Option<A>.map(f: (A) -> B): Option<B> =
            when(this) {
                is None -> this
                is Some -> Some(f(this.get))
            }
    }
}

data class Some<out A>(val get: A) : Option<A>()
object None : Option<Nothing>()


