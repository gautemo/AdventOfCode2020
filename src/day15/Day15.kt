package day15

fun countGame(startingNumbers: List<Int>, stopAt: Int): Int{
    val counting = mutableMapOf<Int, Int>()
    var last = -1
    for((i, number) in startingNumbers.withIndex()){
        counting[number] = i + 1
        last = number
    }

    var diff = 0

    for(i in startingNumbers.size + 1..stopAt){
        last = diff
        diff = i - (counting[last] ?: i)
        counting[last] = i
    }

    return last
}

fun main(){
    val numbers = "10,16,6,0,1,17".split(',').map { it.toInt() }
    val result2020 = countGame(numbers, 2020)
    println(result2020)
    val result30000000 = countGame(numbers, 30000000)
    println(result30000000)
}