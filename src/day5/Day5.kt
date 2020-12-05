package day5

import shared.getLines
import kotlin.math.ceil

fun getSeatId(passport: String): Int{
    var rowMin = 0
    var rowMax = 127
    var columnMin = 0
    var columnMax = 7
    for(i in 0..6){
        val char = passport[i]
        val diff = rowMax - rowMin
        if(char == 'F'){
            rowMax -= ceil((diff / 2.0)).toInt()
        }else{
            rowMin += ceil((diff / 2.0)).toInt()
        }
    }
    for(i in 7..9){
        val char = passport[i]
        val diff = columnMax - columnMin
        if(char == 'L'){
            columnMax -= ceil((diff / 2.0)).toInt()
        }else{
            columnMin += ceil((diff / 2.0)).toInt()
        }
    }
    return rowMin * 8 + columnMin
}

fun findSeat(ids: List<Int>): Int{

    for(id in ids){
        if(!ids.contains(id - 1) && ids.contains(id - 2)) return id - 1
        if(!ids.contains(id + 1) && ids.contains(id + 2)) return id + 1
    }
    return -1
}

fun main(){
    val boardingPasses = getLines("day5.txt")
    val seatIds = boardingPasses.map { getSeatId(it) }
    val highestSeatId = seatIds.max()
    println(highestSeatId)
    val mySeat = findSeat(seatIds)
    println(mySeat)
}