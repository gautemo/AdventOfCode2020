package day23

fun crabCupGame(cupsInput: List<Int>, moves: Int = 100): Map<Int, Cup>{
    val max = cupsInput.max()!!
    var current = Cup(cupsInput[0], null)
    var last = current
    val mapByLabel = mutableMapOf<Int, Cup>()
    mapByLabel[current.label] = current
    for(label in cupsInput){
        if(current.label == label) continue
        val cup = Cup(label, null)
        last.next = cup
        last = cup
        mapByLabel[label] = cup
    }
    last.next = current

    fun findDestination(currentLabel: Int, exclude: List<Int>): Cup{
        var toLabel = currentLabel
        do {
            toLabel--
            if(toLabel <= 0) toLabel = max
        }while (exclude.contains(toLabel))
        return mapByLabel[toLabel]!!
    }

    repeat(moves){
        val take1 = current.next
        val take2 = take1.next
        val take3 = take2.next
        current.next = take3.next
        val dest = findDestination(current.label, listOf(take1.label, take2.label, take3.label))
        take3.next = dest.next
        dest.next = take1
        current = current.next
    }
    return mapByLabel
}

fun getCupGameChainAfter1(input: String): String{
    val map = crabCupGame(input.map { it.toString().toInt() })
    var result = ""
    var check = map[1]?.next ?: error("game lost cup labeled 1")
    while(check.label != 1){
        result += check.label
        check = check.next
    }
    return result
}

fun getStarsCupGame(input: String): Long{
    val cups = millionCups(input)
    val map = crabCupGame(cups, 10_000_000)
    val one = map[1] ?: error("game lost cup labeled 1")
    return one.next.label.toLong() * one.next.next.label.toLong()
}

fun millionCups(input: String): List<Int>{
    val list = input.map { it.toString().toInt() }.toMutableList()
    for(i in input.length + 1..1_000_000){
        list.add(i)
    }
    return list
}

class Cup(val label: Int, next: Cup?){
    var next = next ?: this
}

fun main(){
    val input = "962713854"
    val result = getCupGameChainAfter1(input)
    println(result)
    val stars = getStarsCupGame(input)
    println(stars)
}