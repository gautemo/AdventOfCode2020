package day11

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day11 {
    @Test
    fun `37 seats should end up occupied`(){
        val input = """
            L.LL.LL.LL
            LLLLLLL.LL
            L.L.L..L..
            LLLL.LL.LL
            L.LL.LL.LL
            L.LLLLL.LL
            ..L.L.....
            LLLLLLLLLL
            L.LLLLLL.L
            L.LLLLL.LL
        """.trimIndent()
        val result = occupiedSeats(input)
        assertEquals(37, result)
    }

    @Test
    fun `26 seats should end up occupied on seeing straight`(){
        val input = """
            L.LL.LL.LL
            LLLLLLL.LL
            L.L.L..L..
            LLLL.LL.LL
            L.LL.LL.LL
            L.LLLLL.LL
            ..L.L.....
            LLLLLLLLLL
            L.LLLLLL.L
            L.LLLLL.LL
        """.trimIndent()
        val result = occupiedSeats(input, true, 5)
        assertEquals(26, result)
    }
}