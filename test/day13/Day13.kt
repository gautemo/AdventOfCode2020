package day13

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class Day13 {
    @Test
    fun `ID times wait is 295`(){
        val input = """
            939
            7,13,x,x,59,x,31,19
        """.trimIndent()
        val result = waitTimesId(input)
        assertEquals(295, result)
    }

    @ParameterizedTest
    @MethodSource("ids")
    fun `timestamp where busses leaves`(ids: String, expect: Long){
        val result = timestampForIndexTimes(ids)
        assertEquals(expect, result)
    }

    companion object{
        @JvmStatic
        fun ids() = listOf(
                Arguments.of("7,13,x,x,59,x,31,19", 1068781L),
                Arguments.of("67,7,59,61", 754018L),
                Arguments.of("67,x,7,59,61", 779210L),
                Arguments.of("67,7,x,59,61", 1261476L),
                Arguments.of("1789,37,47,1889", 1202161486L)
        )
    }
}