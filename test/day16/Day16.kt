package day16

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day16 {
    @Test
    fun `ticket invalid numbers gives error rate 71`(){
        val input = """
            class: 1-3 or 5-7
            row: 6-11 or 33-44
            seat: 13-40 or 45-50

            your ticket:
            7,1,14

            nearby tickets:
            7,3,47
            40,4,50
            55,2,20
            38,6,12
        """.trimIndent().lines()
        val result = ticketInvalid(input)
        assertEquals(71, result)
    }
}