package day17

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day17 {
    @Test
    fun `cube state in 6 cycles should be 112`(){
        val input = """
            .#.
            ..#
            ###
        """.trimIndent()
        val result = cubeState(input, 6)
        assertEquals(112, result)
    }

    @Test
    fun `cube 4D state in 6 cycles should be 848`(){
        val input = """
            .#.
            ..#
            ###
        """.trimIndent()
        val result = cubeState(input, 6, 4)
        assertEquals(848, result)
    }
}