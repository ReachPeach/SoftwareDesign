package LRUCache

class LRUCache<K : Any, V : Any>(private val maxCapacity: Int) {
    private val cachedElements: MutableMap<K, Node<K, V>> = mutableMapOf()
    private val queue = LinkedQueue<K, V>()

    operator fun get(key: K): V? {
        var value: V? = null
        if (key in cachedElements) {
            value = cachedElements[key]!!.value
            queue.moveToFront(cachedElements[key]!!)
        }
        checkSizeInvariant()
        return value
    }

    operator fun set(key: K, value: V) {
        if (key in cachedElements) {
            cachedElements[key]!!.value = value
            queue.moveToFront(cachedElements[key]!!)
        } else {
            cachedElements[key] = Node(key, value)
            queue.pushFront(cachedElements[key]!!)
            if (cachedElements.size > maxCapacity) {
                val popped = queue.popBack();
                cachedElements.remove(popped.key)
            }
        }
        checkSizeInvariant()
        assert(key in cachedElements)
        assert(key == cachedElements[key]!!.key)
        assert(value == cachedElements[key]!!.value)
    }

    private fun checkSizeInvariant() {
        assert(cachedElements.size <= maxCapacity)
    }
}

class LinkedQueue<K : Any, V : Any> {

    private var head = Node<K, V>(null, null)
    private var tail = Node<K, V>(null, null)

    init {
        head.previous = tail
        tail.next = head;
    }

    fun popBack(): Node<K, V> {
        assert(head.previous != tail)
        assert(tail.next != head)
        val popped = tail.next
        tail.next = tail.next!!.next
        tail.next!!.previous = tail
        assert(popped != null)
        return popped!!
    }

    fun moveToFront(node: Node<K, V>) {
        node.previous!!.next = node.next
        node.next!!.previous = node.previous

        node.next = head
        head.previous!!.next = node
        node.previous = head.previous
        head.previous = node
    }

    fun pushFront(node: Node<K, V>) {
        head.previous!!.next = node
        node.previous = head.previous
        node.next = head
        head.previous = node
    }
}


class Node<K, V>(val key: K?, var value: V?) {
    var previous: Node<K, V>? = null
    var next: Node<K, V>? = null
}
