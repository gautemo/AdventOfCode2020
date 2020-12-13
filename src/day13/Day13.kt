package day13

import shared.getText

fun waitTimesId(input: String): Int{
    val (earliestString, idsString) = input.lines()
    val earliest = earliestString.toInt()
    val ids = idsString.split(",").filter { it != "x" }.map { it.toInt() }

    var shortest = Int.MAX_VALUE
    var shortestId = 0
    for(id in ids){
        val wait = -(earliest % id) + id
        shortest = minOf(wait, shortest)
        if(shortest == wait) shortestId = id
    }
    return shortest * shortestId
}

fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)
fun lcm(a: Long, b: Long): Long = a / gcd(a, b) * b

fun timestampForIndexTimes(input: String, startAt: Long = 0): Long{
    val ids = input.split(",")
    var skip = ids[0].toLong()
    var t = startAt - (startAt % skip)

    while(true){
        val alligns = ids.withIndex().filter { (i, id) ->
            if(id == "x") {
                false
            }else{
                (t + i ) % id.toInt() == 0L
            }
        }.map { (_, id) -> id.toLong() }

        if(alligns.size == ids.count { it != "x" }){
            return t
        }
        val lcmOfAlligns = alligns.reduce { acc, l -> lcm(acc, l) }
        skip = maxOf(skip, lcmOfAlligns)
        t += skip
     }
}

fun main(){
    val input = getText("day13.txt")
    val result = waitTimesId(input)
    println(result)
    val timestamp = timestampForIndexTimes(input.lines()[1], 100000000000000)
    println(timestamp)
}