package fpinkotlin

sealed class Option<out A> {
    companion object {
        fun <A, B> Option<A>.map(f: (A) -> B): Option<B> =
            when(this) {
                is None -> this
                is Some -> Some(f(this.get))
            }

        fun <A, B> Option<A>.mapInTermsOfFlatMap(f: (A) -> B): Option<B> =
            this.flatMap { a -> Some(f(a))}

        fun <A, B> Option<A>.flatMap(f: (A) -> Option<B>): Option<B> =
            when (this) {
                is None -> this
                is Some -> f(this.get)
            }

        fun <A> Option<A>.getOrElse(default: () -> A): A =
            when (this) {
                is None -> default()
                is Some -> this.get
            }
        // none -> call the function - none
        // none -> call the function - some
        // some -> some
        fun <A> Option<A>.orElse(ob: () -> Option<A>): Option<A> =
            when (this) {
                is None -> ob()
                is Some -> this
            }
        // Can't figure out if this is possible to implement orElse without pattern matching
//        fun <A> Option<A>.orElseWithoutMatch(ob: () -> Option<A>): Option<A> {
//
//            val result = this.getOrElse {
//                val maybeA = ob()
//                maybeA
//            }
//
//            return Some(result)
//        }
    }
}

data class Some<out A>(val get: A) : Option<A>()
object None : Option<Nothing>()


