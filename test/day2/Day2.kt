package day2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day2 {
    @Test
    fun `should find two passwords`(){
        val input = """
            1-3 a: abcde
            1-3 b: cdefg
            2-9 c: ccccccccc
        """.trimIndent().lines()
        val result = findValidPasswords(input)
        assertEquals(2, result)
    }

    @Test
    fun `should find one passwords`(){
        val input = """
            1-3 a: abcde
            1-3 b: cdefg
            2-9 c: ccccccccc
        """.trimIndent().lines()
        val result = findValidPasswords2(input)
        assertEquals(1, result)
    }
}