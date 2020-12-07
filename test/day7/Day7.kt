package day7

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day7 {
    @Test
    fun `4 bag colors can contain at least one gold bag`(){
        val result = nrCanContainGoldBag(input)
        assertEquals(4, result)
    }

    @Test
    fun `gold bag contains 32 bags`(){
        val result = bagsInsideGold(input)
        assertEquals(32, result)
    }

    companion object{
        val input = """
            light red bags contain 1 bright white bag, 2 muted yellow bags.
            dark orange bags contain 3 bright white bags, 4 muted yellow bags.
            bright white bags contain 1 shiny gold bag.
            muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
            shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
            dark olive bags contain 3 faded blue bags, 4 dotted black bags.
            vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
            faded blue bags contain no other bags.
            dotted black bags contain no other bags.
        """.trimIndent().lines()
    }
}