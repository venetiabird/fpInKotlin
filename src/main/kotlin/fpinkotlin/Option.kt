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

        fun <A> Option<A>.filterWithMatch(f: (A) -> Boolean): Option<A> =
            when (this) {
                is None -> None
                is Some -> if(f(this.get)) this else None
            }

        fun <A> Option<A>.filter(f: (A) -> Boolean): Option<A> =
            this.flatMap { a -> if(f(a)) Some(a) else None }

        fun <A, B> lift(f: (A) -> B): (Option<A>) -> Option<B> =
            fun(oa: Option<A>): Option<B> = oa.map(f)

        fun insuranceRateQuote(
            age: Int,
            numberOfSpeedingTickets: Int
        ): Double = TODO()

        fun parseInsuranceRateQuote(
            age: String,
            speedingTickets: String
        ): Option<Double> {
            val optAge: Option<Int> = catches { age.toInt() }
            val optTickets: Option<Int> =
            catches { speedingTickets.toInt() }
            return map2(optAge, optTickets) { a, t ->
                insuranceRateQuote(a, t)
            }
        }

        fun <A> catches(a: () -> A): Option<A> =
            try {
                Some(a())
            } catch (e: Throwable) {
                None
            }

        // Ex 4.3
        fun <A, B, C> map2(oa: Option<A>, ob: Option<B>, f: (A, B) -> C): Option<C> =
            oa.flatMap { a ->
                ob.map { b -> f(a, b) }
            }

        // Ex 4.4
        fun <A> sequence(xs: Lizt<Option<A>>): Option<Lizt<A>> =
            Lizt.foldLeft(xs, Some(Nil as Lizt<A>)) { ob: Option<Lizt<A>>, oa: Option<A> ->
                // map over the option, and join to the list
                map2(oa, ob) { a, aas -> Lizt.appendRight(aas, a)}
            }

        // Ex 4.5
        fun <A, B> traverse(
            xa: Lizt<A>,
            f: (A) -> Option<B>
        ): Option<Lizt<B>> =
            Lizt.foldLeft(xa, Some(Nil) as Option<Lizt<B>>, { ob, a: A ->
                when (ob) {
                    is None -> None
                    is Some -> {
                        when (val optionB = f(a)) {
                            is None -> None
                            is Some -> Some(Lizt.appendRight(ob.get, optionB.get))
                        }
                    }
                }
            })
    }
}

data class Some<out A>(val get: A) : Option<A>()
object None : Option<Nothing>() {
    override fun toString(): String = "None"
}


