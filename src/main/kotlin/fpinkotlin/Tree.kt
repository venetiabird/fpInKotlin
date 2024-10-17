package fpinkotlin

data class Leaf<A>(val value: A) : Tree<A>()
data class Branch<A>(val left: Tree<A>, val right: Tree<A>) : Tree<A>()
sealed class Tree<out A> {
    companion object {
        // Exercise 3.25
        fun maximum(tree: Tree<Int>): Int = when(tree) {
            is Leaf -> tree.value
            is Branch -> maxOf(maximum(tree.left), maximum(tree.right))
        }

        // Exercise 3.26
        fun depth(tree: Tree<Int>): Int = when(tree) {
            is Leaf -> 0
            is Branch -> maxOf(depth(tree.left), depth(tree.right)) + 1
        }

        // 3.28
        fun <A, B> fold(ta: Tree<A>, l: (A) -> B, b: (B, B) -> B): B =
            when(ta) {
                is Leaf -> l(ta.value)
                is Branch -> {
                    val b1: B = fold(ta.left, l, b)
                    val b2: B = fold(ta.right, l, b)
                    b(b1, b2)
                }
            }


        // 3.28
        fun <A> sizeF(ta: Tree<A>): Int =
            fold(
                ta,
                { _ -> 1},
                { b1, b2 -> 1 + b1 + b2}
            )

        // 3.28
        fun maximumF(ta: Tree<Int>): Int =
            fold(
                ta,
                { it },
                { bl, br -> maxOf(bl, br) }
            )

        // 3.28
        fun <A> depthF(ta: Tree<A>): Int =
            fold(
                ta,
                { _ -> 0 },
                { bl, br -> 1 + maxOf(bl, br)}
            )

        // 3.28
        fun <A, B> mapF(ta: Tree<A>, f: (A) -> B): Tree<B> =
            fold(
                ta,
                { a: A -> Leaf(f(a)) },
                { b1: Tree<B>, b2: Tree<B> -> Branch(b1, b2) }
            )

    }

    // Exercise 3.24
    fun size(): Int {
        when (this) {
            is Leaf -> return 1
            is Branch -> return 1 + this.left.size() + this.right.size()
        }
    }

    // Exercise 3.27
    fun <B>map(fn: (A) -> B): Tree<B> =
        when(this) {
            is Leaf -> Leaf(fn(this.value))
            is Branch -> Branch(this.left.map(fn), this.right.map(fn))
        }

}

