package day3

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day3 {
    @Test
    fun `trees encountered`(){
        val input = """
            ..##.......
            #...#...#..
            .#....#..#.
            ..#.#...#.#
            .#...##..#.
            ..#.##.....
            .#.#.#....#
            .#........#
            #.##...#...
            #...##....#
            .#..#...#.#
        """.trimIndent().lines()
        val result = countTrees(input, Player(3,1))
        assertEquals(7, result)
    }

    @Test
    fun `trees encountered multiplied`(){
        val input = """
            ..##.......
            #...#...#..
            .#....#..#.
            ..#.#...#.#
            .#...##..#.
            ..#.##.....
            .#.#.#....#
            .#........#
            #.##...#...
            #...##....#
            .#..#...#.#
        """.trimIndent().lines()
        val result = multiplyListedSlopeTrees(input)
        assertEquals(336, result)
    }
}