package day11

import shared.XYMap
import shared.getText

fun occupiedSeats(seats: String, lookStraight: Boolean = false, toMuch: Int = 4): Int{
    var lastRound: XYMap? = null
    var currentSeats = XYMap(seats)
    while(lastRound?.map != currentSeats.map){
        lastRound = currentSeats
        currentSeats = tick(currentSeats, lookStraight, toMuch)
    }
    return currentSeats.countChar('#')
}

fun tick(seats: XYMap, lookStraight: Boolean = false, toMuch: Int = 4): XYMap{
    val newSeats = XYMap(seats.map)
    for(y in 0 until seats.height){
       for(x in 0 until seats.width){
           val seat = seats.getAt(x, y)
           if(seat == '.') continue
           val adjacent = if(lookStraight) countSight(seats, x, y) else seats.countAdjacent(x, y, '#')
           when{
               seat == 'L' && adjacent == 0 -> newSeats.setAt(x, y, '#')
               seat == '#' && adjacent >= toMuch -> newSeats.setAt(x, y, 'L')
           }
       }
    }
    return newSeats
}

fun countSight(seats: XYMap, x: Int, y: Int): Int{
    var taken = 0

    if(look(seats, x, y, Direction.STILL, Direction.UP)) taken++
    if(look(seats, x, y, Direction.RIGHT, Direction.UP)) taken++
    if(look(seats, x, y, Direction.RIGHT, Direction.STILL)) taken++
    if(look(seats, x, y, Direction.RIGHT, Direction.DOWN)) taken++
    if(look(seats, x, y, Direction.STILL, Direction.DOWN)) taken++
    if(look(seats, x, y, Direction.LEFT, Direction.DOWN)) taken++
    if(look(seats, x, y, Direction.LEFT, Direction.STILL)) taken++
    if(look(seats, x, y, Direction.LEFT, Direction.UP)) taken++

    return taken
}

enum class Direction {
    LEFT, RIGHT, UP, DOWN, STILL
}

fun look(seats: XYMap, x: Int, y: Int, dirX: Direction, dirY: Direction): Boolean{
    var checkX = x
    var checkY = y
    while(checkX >= 0 && checkX < seats.width && checkY >= 0 && checkY < seats.height){
        if(dirX == Direction.LEFT) checkX--
        if(dirX == Direction.RIGHT) checkX++
        if(dirY == Direction.UP) checkY--
        if(dirY == Direction.DOWN) checkY++
        if(seats.getAt(checkX, checkY) == '#'){
            return true
        }
        if(seats.getAt(checkX, checkY) == 'L'){
            return false
        }
    }
    return false
}

fun main(){
    val seats = getText("day11.txt")
    val occupied = occupiedSeats(seats)
    println(occupied)
    val occupiedStraight = occupiedSeats(seats, true, 5)
    println(occupiedStraight)
}