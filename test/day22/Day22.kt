package day22

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day22 {
    @Test
    fun `winner score is 306`(){
        val result = combat(input)
        assertEquals(306, result)
    }

    @Test
    fun `winner recursive combat score is 291`(){
        val result = recursiveCombat(input)
        assertEquals(291, result)
    }

    companion object{
        val input = """
            Player 1:
            9
            2
            6
            3
            1

            Player 2:
            5
            8
            4
            7
            10
        """.trimIndent().lines()
    }
}