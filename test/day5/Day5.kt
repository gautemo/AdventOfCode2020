package day5

import org.junit.jupiter.params.ParameterizedTest
import kotlin.test.assertEquals
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day5 {
    @ParameterizedTest
    @MethodSource("boardigPasses")
    fun `boarding pass with seat ID 357`(boardingPass: String, expectId: Int){
        val result = getSeatId(boardingPass)
        assertEquals(expectId, result)
    }

    companion object{
        @JvmStatic
        fun boardigPasses() = listOf(
                Arguments.of("FBFBBFFRLR", 357),
                Arguments.of("BFFFBBFRRR", 567),
                Arguments.of("FFFBBBFRRR", 119),
                Arguments.of("BBFFBBFRLL", 820)
        )
    }
}