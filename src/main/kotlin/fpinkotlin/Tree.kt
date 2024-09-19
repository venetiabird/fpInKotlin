package fpinkotlin

data class Leaf<A>(val value: A) : Tree<A>()
data class Branch<A>(val left: Tree<A>, val right: Tree<A>) : Tree<A>()
sealed class Tree<out A> {
    // Exercise 3.24
    fun size(): Int {
        when (this) {
            is Leaf -> return 1
            is Branch -> return 1 + this.left.size() + this.right.size()
        }
    }
}

