package day12

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day12 {
    @Test
    fun `manhatten distance should be 25`(){
        val input = """
            F10
            N3
            F7
            R90
            F11
        """.trimIndent().lines()
        val result = findDistance(input)
        assertEquals(25, result)
    }

    @Test
    fun `waypoint distance should be 286`(){
        val input = """
            F10
            N3
            F7
            R90
            F11
        """.trimIndent().lines()
        val result = findWaypointDistance(input)
        assertEquals(286, result)
    }
}