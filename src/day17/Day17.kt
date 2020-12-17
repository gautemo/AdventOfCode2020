package day17

import shared.XYZWMap
import shared.getText

fun cubeState(input: String, cycles: Int, dimension: Int = 3): Int{
    var map = XYZWMap(input, dimension)
    for(i in 1..cycles) map = tick(map)
    return map.countChar('#')
}

fun tick(map: XYZWMap): XYZWMap{
    val copyMap = map.copy()
    for(x in map.xRange){
        for(y in map.yRange){
            for(z in map.zRange){
                for(w in map.wRange){
                    val state = map.getAt(x, y, z, w)
                    val activeNeighbors = map.countAdjacent(x, y, z, w, charToCount = '#')
                    if(state == '#' && !listOf(2, 3).contains(activeNeighbors)) copyMap.setAt(x,y,z, w, char = '.')
                    if(state != '#' && activeNeighbors == 3) copyMap.setAt(x,y,z, w, char = '#')
                }
            }
        }
    }
    return copyMap
}

fun main(){
    val input = getText("day17.txt")
    val active = cubeState(input, 6)
    println(active)
    val active4d = cubeState(input, 6, 4)
    println(active4d)
}