package day25

fun publicKeyToSize(pKey: Long): Int{
    var loopSize = 1
    var value = 1L
    while (true){
        value = handShakeStep(value, 7)
        if(value == pKey){
            return loopSize
        }
        loopSize++
    }
}

fun subjectNumberToPublicKey(subjectNumber: Long, loopSize: Int): Long{
    var value = 1L
    repeat(loopSize){
        value = handShakeStep(value, subjectNumber)
    }
    return value
}

fun handShakeStep(value: Long, subjectNumber: Long) = (value * subjectNumber) % 20201227

fun encryptionKey(pKey: Long, loopSize: Int): Long{
    return subjectNumberToPublicKey(pKey, loopSize)
}

fun pKeysToEncryptionKey(pKey1: Long, pKey2: Long): Long{
    val loopSize1 = publicKeyToSize(pKey1)
    return encryptionKey(pKey2, loopSize1)
}

fun main(){
    val pKey1 = 8335663L
    val pKey2 = 8614349L
    val encryptionKey = pKeysToEncryptionKey(pKey1, pKey2)
    println(encryptionKey)
}