package shared

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DemoTests {
    @Test
    fun `something should return the same`(){
        val num = 5
        val result = something(num)
        assertEquals(num, result)
    }
}