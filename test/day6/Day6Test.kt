package day6

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

val input = """
            abc

            a
            b
            c

            ab
            ac

            a
            a
            a
            a

            b
        """.trimIndent()

class Day6Test {
    @Test
    fun `sum of yes should be 11`(){
        val result = sumOfYes(input)
        assertEquals(11, result)
    }

    @Test
    fun `sum of everyone yes should be 6`(){
        val result = sumOfEveryoneYes(input)
        assertEquals(6, result)
    }
}