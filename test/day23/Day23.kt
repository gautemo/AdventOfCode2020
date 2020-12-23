package day23

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day23 {
    @Test
    fun `crab cup game gives 67384529`(){
        val input = "389125467"
        val result = getCupGameChainAfter1(input)
        assertEquals("67384529", result)
    }

    @Test
    fun `crab cup game million gives 149245887792`(){
        val input = "389125467"
        val result = getStarsCupGame(input)
        assertEquals(149245887792, result)
    }
}