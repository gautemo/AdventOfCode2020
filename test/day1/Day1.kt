package day1

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day1 {
    @Test
    fun `should find answer of expense report`(){
        val lines = listOf(
                1721,
                979,
                366,
                299,
                675,
                1456)
        val result = findEntriesOf2020AndMultiply(lines)
        assertEquals(514579, result)
    }

    @Test
    fun `should find three expenses that adds up to 2020 and multiply`(){
        val lines = listOf(
                1721,
                979,
                366,
                299,
                675,
                1456)
        val result = findThreeEntriesOf2020AndMultiply(lines)
        assertEquals(241861950, result)
    }
}