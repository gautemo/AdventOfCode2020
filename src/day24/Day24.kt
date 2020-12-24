package day24

import shared.getLines
import kotlin.math.abs

val commands = listOf("e", "se", "sw", "w", "nw", "ne")

fun turnedTiles(input: List<String>, days: Int = 0): Int{
    val tiles = mutableMapOf<PointDouble, Boolean>()
    for(path in input){
        var x = 0.0
        var y = 0.0
        var pathLeft = path
        while(pathLeft.isNotEmpty()){
            for(c in commands){
                val removed = pathLeft.removePrefix(c)
                if(removed != pathLeft){
                    pathLeft = removed
                    when(c){
                        "e" -> x++
                        "se" -> x += 0.5.also { y += 1 }
                        "sw" -> x -= 0.5.also { y += 1 }
                        "w" -> x--
                        "nw" -> x -= 0.5.also { y -= 1 }
                        "ne" -> x += 0.5.also { y -= 1 }
                    }
                    break
                }
            }
        }
        tiles[(PointDouble(x, y))] = !(tiles[(PointDouble(x, y))] ?: false)
    }

    var mapForDay = TileMap(tiles)
    repeat(days){
        mapForDay = TileMap(mapForDay.blackTilesNextDay())
    }

    return mapForDay.tiles.count { it.value }
}

data class PointDouble(val x: Double, val y: Double)

class TileMap(val tiles: MutableMap<PointDouble, Boolean>){

    private fun countAdjacent(x: Double, y: Double): Int{
        return tiles.count { (point, black) ->
            !(point.x == x && point.y == y) &&
            abs(point.x - x) <= 1 &&
            abs(point.y - y) <= 1 &&
            black
        }
    }

    private fun pointsToCheck(): List<PointDouble>{
        val points = mutableListOf<PointDouble>()
        var y = tiles.minBy { it.key.y }!!.key.y - 1
        while(y <= tiles.maxBy { it.key.y }!!.key.y + 1){
            var x = (tiles.minBy { it.key.x }!!.key.x - 1).toInt().toDouble()
            if(y % 2 != 0.0){
                x += 0.5
            }
            while(x <= tiles.maxBy { it.key.x }!!.key.x + 1){
                points.add(PointDouble(x, y))
                x++
            }
            y++
        }
        return points
    }

    fun blackTilesNextDay(): MutableMap<PointDouble, Boolean>{
        val copy = tiles.toMutableMap()
        val check = pointsToCheck()
        for(p in check){
            val adjacents = countAdjacent(p.x, p.y)
            if(tiles[p] == true && (adjacents == 0 || adjacents > 2)){
                copy[p] = false
            }
            if(tiles[p] != true && adjacents == 2){
                copy[p] = true
            }
        }
        return copy
    }
}

fun main(){
    val input = getLines("day24.txt")
    val blackTiles = turnedTiles(input)
    println(blackTiles)
    val blackTiles100Days = turnedTiles(input, 100)
    println(blackTiles100Days)
}