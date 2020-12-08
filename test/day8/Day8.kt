package day8

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day8 {
    @Test
    fun `acc is 5 on loop`(){
        val result = finAccOnLoop(input)
        assertEquals(5, result)
    }

    @Test
    fun `acc is 8 on fix`(){
        val result = finAccOnFixedLoop(input)
        assertEquals(8, result)
    }

    companion object{
        val input = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
        """.trimIndent().lines()
    }
}