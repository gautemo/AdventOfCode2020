package day10

import shared.getLinesAsInt

fun adaptersDifferenceCalculation(adapters: List<Int>): Int{
    val joltDiff = sortedWithChargerAndDevice(adapters).zipWithNext().map { it.second - it.first }
    return joltDiff.count { it == 1 } * joltDiff.count { it == 3 }
}

fun adaptersCombination(adapters: List<Int>): Long{
    return comb(sortedWithChargerAndDevice(adapters))
}

val cache = mutableMapOf<String, Long>()

fun comb(adapters: List<Int>): Long{
    return cache.getOrElse(adapters.joinToString()){
        var combinations = 0L
        if(adapters.size > 2) {
            combinations += comb(adapters.subList(1, adapters.size))
        }else{
            combinations++
        }
        if(adapters.size > 2 && adapters[2] - adapters[0] <= 3){
            combinations += comb(adapters.subList(2, adapters.size))
        }
        if(adapters.size > 3 && adapters[3] - adapters[0] <= 3){
            combinations += comb(adapters.subList(3, adapters.size))
        }
        cache[adapters.joinToString()] = combinations
        combinations
    }
}

fun sortedWithChargerAndDevice(adapters: List<Int>): List<Int>{
    return listOf(0) + adapters.sorted() + listOf(adapters.max()!! + 3)
}

fun main(){
    val adapters = getLinesAsInt("day10.txt")
    val result = adaptersDifferenceCalculation(adapters)
    println(result)
    val combinations = adaptersCombination(adapters)
    println(combinations)
}