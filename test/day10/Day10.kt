package day10

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day10 {
    @Test
    fun `adapters difference 1 multiplied difference 3 should be 35`(){
        val input = """
            16
            10
            15
            5
            1
            11
            7
            19
            6
            12
            4
        """.trimIndent().lines().map { it.toInt() }
        val result = adaptersDifferenceCalculation(input)
        assertEquals(35, result)
    }

    @Test
    fun `adapters combination should be 8`(){
        val input = """
            16
            10
            15
            5
            1
            11
            7
            19
            6
            12
            4
        """.trimIndent().lines().map { it.toInt() }
        val result = adaptersCombination(input)
        assertEquals(8, result)
    }

    @Test
    fun `adapters combination should be 19208`(){
        val input = """
            28
            33
            18
            42
            31
            14
            46
            20
            48
            47
            24
            23
            49
            45
            19
            38
            39
            11
            1
            32
            25
            35
            8
            17
            7
            9
            4
            2
            34
            10
            3
        """.trimIndent().lines().map { it.toInt() }
        val result = adaptersCombination(input)
        assertEquals(19208, result)
    }
}