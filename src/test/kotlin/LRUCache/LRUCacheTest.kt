package LRUCache

import org.junit.Test

class LRUCacheTest {
    @Test
    fun `1 element storage`() {
        val cache = LRUCache<Int, Int>(1)
        cache[0] = 0
        assert(cache[0]?.equals(0) ?: false)
    }

    @Test
    fun `last added 1-element storage`() {
        val cache = LRUCache<Int, Int>(1)
        for (i in 0..10) cache[i] = i
        assert(cache[10]?.equals(10) ?: false)
    }

    @Test
    fun `last added n-element storage`() {
        val cache = LRUCache<Int, Int>(3)
        for (i in 0..10) cache[i] = i
        for (i in 0..6) assert(cache[i] == null)
        for (i in 8..10) assert(cache[i]?.equals(i) ?: false)
    }

    @Test
    fun `last written elements in storage`() {
        val cache = LRUCache<Int, Int>(10)
        for (j in 1..3) {
            for (i in 1..10) cache[i] = j * i
        }
        for (i in 1..10) assert(cache[i]?.equals(i * 3) ?: false)
    }

    @Test
    fun `order in queue should change after get-request`() {
        val cache = LRUCache<Int, Int>(3)
        for (i in 0..2) cache[i] = i
        for (i in 0..1) cache[i]
        cache[3] = 3
        assert(cache[2] == null)
        assert(cache[3]?.equals(3) ?: false)
        for (i in 0..1) assert(cache[i]?.equals(i) ?: false)
    }

}