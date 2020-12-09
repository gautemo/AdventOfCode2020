package day9

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day9 {
    @Test
    fun `XMAS weakness should be 127`(){
        val result = xmasWeakness(input, 5)
        assertEquals(127, result)
    }

    @Test
    fun `XMAS weakness part 2 should be 62`(){
        val result = xmasWeaknessPart2(input, 5)
        assertEquals(62, result)
    }

    companion object{
        val input = """
            35
            20
            15
            25
            47
            40
            62
            55
            65
            95
            102
            117
            150
            182
            127
            219
            299
            277
            309
            576
        """.trimIndent().lines().map { it.toLong() }
    }
}