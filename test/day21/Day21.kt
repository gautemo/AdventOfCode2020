package day21

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day21 {
    @Test
    fun `non allergens ingredients listed 5 times`(){
        val result = nonAllergensCount(input)
        assertEquals(5, result)
    }

    @Test
    fun `dangerousList is mxmxvkd,sqjhc,fvjkl`(){
        val result = dangerousList(input)
        assertEquals("mxmxvkd,sqjhc,fvjkl", result)
    }

    companion object{
        val input = """
            mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
            trh fvjkl sbzzf mxmxvkd (contains dairy)
            sqjhc fvjkl (contains soy)
            sqjhc mxmxvkd sbzzf (contains fish)
        """.trimIndent().lines()
    }
}