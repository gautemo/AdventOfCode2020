package day25

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class Day25 {
    @ParameterizedTest
    @MethodSource("publicKey")
    fun `public key to loop size`(pKey: Long, expected: Int){
        val result = publicKeyToSize(pKey)
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("subjectKeyLoopSizePublicKey")
    fun `subject key and loop size gives correct public key`(subjectNumber: Long, loopSize: Int, expected: Long){
        val result = subjectNumberToPublicKey(subjectNumber, loopSize)
        assertEquals(expected, result)
    }

    @Test
    fun `public key of door with key loops size give encryption key`(){
        val result = encryptionKey(17807724, 8)
        assertEquals(14897079, result)
    }

    @Test
    fun `public keys to encryption key`(){
        val result = pKeysToEncryptionKey(5764801, 17807724)
        assertEquals(14897079, result)
    }

    companion object{
        @JvmStatic
        fun publicKey() = listOf(
                Arguments.of(5764801, 8),
                Arguments.of(17807724, 11)
        )

        @JvmStatic
        fun subjectKeyLoopSizePublicKey() = listOf(
                Arguments.of(7, 8, 5764801),
                Arguments.of(7, 11, 17807724)
        )
    }
}