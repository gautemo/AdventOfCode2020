package day14

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day14 {
    @Test
    fun `program initialization sum is 165`(){
        val input = """
            mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
            mem[8] = 11
            mem[7] = 101
            mem[8] = 0
        """.trimIndent().lines()
        val result = initProgram(input)
        assertEquals(165, result)
    }

    @Test
    fun `program v2 gives sum 208`(){
        val input = """
            mask = 000000000000000000000000000000X1001X
            mem[42] = 100
            mask = 00000000000000000000000000000000X0XX
            mem[26] = 1
        """.trimIndent().lines()
        val result = initProgram(input, true)
        assertEquals(208, result)
    }
}