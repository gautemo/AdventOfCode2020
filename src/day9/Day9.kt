package day9

import shared.getLines

fun xmasWeakness(numbers: List<Long>, preamble: Int = 25): Long{
    for(i in preamble until numbers.size){
        val toCheck = allPairs(numbers.take(i).takeLast(preamble))
        val valid = toCheck.any { it.first + it.second == numbers[i] }
        if(!valid) return numbers[i]
    }
    return -1
}

fun allPairs(numbers: List<Long>): List<Pair<Long, Long>>{
    val pairs = mutableListOf<Pair<Long, Long>>()
    for(number1 in numbers){
        for(number2 in numbers){
            if(number1 != number2 && !pairs.any { (it.first == number1 && it.second == number2) || (it.first == number2 && it.second == number1) }){
                pairs.add(Pair(number1, number2))
            }
        }
    }
    return pairs
}

fun xmasWeaknessPart2(numbers: List<Long>, preamble: Int = 25): Long{
    val weaknessNr = xmasWeakness(numbers, preamble)
    for(i in numbers.indices){
        for(j in i + 1 until numbers.size){
            val toCheck = numbers.subList(i, j)
            if(toCheck.sum() == weaknessNr){
                return toCheck.min()!! + toCheck.max()!!
            }else if(toCheck.sum() > weaknessNr){
                break
            }
        }
    }
    return -1
}

fun main(){
    val numbers = getLines("day9.txt").map { it.toLong() }
    val weakness = xmasWeakness(numbers)
    println(weakness)
    val weakness2 = xmasWeaknessPart2(numbers)
    println(weakness2)
}