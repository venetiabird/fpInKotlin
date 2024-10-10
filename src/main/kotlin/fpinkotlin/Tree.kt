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

    }
    // Exercise 3.24
    fun size(): Int {
        when (this) {
            is Leaf -> return 1
            is Branch -> return 1 + this.left.size() + this.right.size()
        }
    }

    fun <B>map(fn: (A) -> B): Tree<B> =
        when(this) {
            is Leaf -> Leaf(fn(this.value))
            is Branch -> Branch(this.left.map(fn), this.right.map(fn))
        }
}

