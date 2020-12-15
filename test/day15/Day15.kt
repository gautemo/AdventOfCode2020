package day15

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day15 {
    @Test
    fun `2020th should be 436`(){
        val input = "0,3,6".split(',').map { it.toInt() }
        val result = countGame(input, 2020)
        assertEquals(436, result)
    }

    @Test
    fun `30000000th should be 175594`(){
        val input = "0,3,6".split(',').map { it.toInt() }
        val result = countGame(input, 30000000)
        assertEquals(175594, result)
    }
}