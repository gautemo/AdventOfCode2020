package day18

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class Day18 {
    @Test
    fun `expression should be 71`(){
        val expression = "1 + 2 * 3 + 4 * 5 + 6"
        val result = calculate(expression)
        assertEquals(71, result)
    }

    @ParameterizedTest
    @MethodSource("calculations")
    fun `expression with parentheses should be correct`(expression: String, expect: Long){
        val result = calculate(expression)
        assertEquals(expect, result)
    }

    @ParameterizedTest
    @MethodSource("calculationsPrecedence")
    fun `expression addition precedence`(expression: String, expect: Long){
        val result = calculatePrecedence(expression)
        assertEquals(expect, result)
    }

    companion object{
        @JvmStatic
        fun calculations() = listOf(
                Arguments.of("1 + (2 * 3) + (4 * (5 + 6))", 51L),
                Arguments.of("2 * 3 + (4 * 5)", 26L),
                Arguments.of("5 + (8 * 3 + 9 + 3 * 4 * 3)", 437L),
                Arguments.of("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", 12240L),
                Arguments.of("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", 13632L)
        )

        @JvmStatic
        fun calculationsPrecedence() = listOf(
                Arguments.of("1 + 2 * 3 + 4 * 5 + 6", 231L),
                Arguments.of("1 + (2 * 3) + (4 * (5 + 6))", 51L),
                Arguments.of("2 * 3 + (4 * 5)", 46L),
                Arguments.of("5 + (8 * 3 + 9 + 3 * 4 * 3)", 1445L),
                Arguments.of("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", 669060L),
                Arguments.of("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", 23340L)
        )
    }
}