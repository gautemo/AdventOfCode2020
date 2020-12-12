package day12

import shared.getLines
import kotlin.math.abs

fun findDistance(commands: List<String>): Int{
    var x = 0
    var y = 0
    var facing = 90
    for(command in commands){
        val action = command[0]
        val value = command.substring(1).toInt()
        when(action){
            'N' -> y -= value
            'S' -> y += value
            'E' -> x += value
            'W' -> x -= value
            'L' -> facing -= value
            'R' -> facing += value
            'F' -> {
                when(((facing % 360) + 360) % 360){
                    0 -> y -= value
                    90 -> x += value
                    180 -> y += value
                    270 -> x -= value
                }
            }
        }
    }

    return abs(x) + abs(y)
}

fun findWaypointDistance(commands: List<String>): Int{
    var x = 0
    var y = 0
    var wX = 10
    var wY = -1
    for(command in commands){
        val action = command[0]
        val value = command.substring(1).toInt()
        when(action){
            'N' -> wY -= value
            'S' -> wY += value
            'E' -> wX += value
            'W' -> wX -= value
            'L' -> {
                when(value){
                    90 -> {
                        wX = wY.also { wY = -wX }
                    }
                    180 -> {
                        wX = -wX
                        wY = -wY
                    }
                    270 -> {
                        wX = -wY.also { wY = wX }
                    }
                }
            }
            'R' -> {
                when(value){
                    90 -> {
                        wX = -wY.also { wY = wX }
                    }
                    180 -> {
                        wX = -wX
                        wY = -wY
                    }
                    270 -> {
                        wX = wY.also { wY = -wX }
                    }
                }
            }
            'F' -> {
                x += wX * value
                y += wY * value
            }
        }
    }

    return abs(x) + abs(y)
}

fun main(){
    val commands = getLines("day12.txt")
    val distance = findDistance(commands)
    println(distance)
    val waypointDistance = findWaypointDistance(commands)
    println(waypointDistance)
}