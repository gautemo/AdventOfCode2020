package day17

import shared.DimensionMap
import shared.getText

fun cubeStateAlt(input: String, cycles: Int, dimension: Int = 3): Int{
    var map = DimensionMap(input, dimension)
    for(i in 1..cycles) map = tickAlt(map)
    return map.countChar('#')
}

fun tickAlt(map: DimensionMap): DimensionMap{
    val copyMap = map.copy()

    fun forPoints(points: List<Int>){
        println(points)
        if(points.size == map.ranges.size){
            val state = map.getAt(*points.toIntArray())
            val activeNeighbors = map.countAdjacent('#', *points.toIntArray())
            if(state == '#' && !listOf(2, 3).contains(activeNeighbors)) copyMap.setAt('.', *points.toIntArray())
            if(state != '#' && activeNeighbors == 3) copyMap.setAt('#', *points.toIntArray())
        }else{
            for(d in map.ranges[points.size]!!){
                forPoints(points + d)
            }
        }
    }

    forPoints(listOf())

    return copyMap
}

fun main(){
    val input = getText("day17.txt")
    val active = cubeStateAlt(input, 6)
    println(active)
    val active4d = cubeStateAlt(input, 6, 4)
    println(active4d)
}
