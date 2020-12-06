package day6

import shared.getText
import shared.separateByBlankLine

fun sumOfYes(input: String): Int{
    val groups = separateByBlankLine(input)
    var sum = 0
    for(group in groups){
        sum += group.filter { !it.isWhitespace() }.toList().distinct().size
    }
    return sum
}

fun sumOfEveryoneYes(input: String): Int{
    val groups = separateByBlankLine(input)
    var sum = 0
    for(group in groups){
        sum += group.lines().reduce { acc, s -> (acc.toList() intersect s.toList()).joinToString("") }.length
    }
    return sum
}

fun main(){
    val input = getText("day6.txt")
    val someYes = sumOfYes(input)
    println(someYes)
    val allYes = sumOfEveryoneYes(input)
    println(allYes)
}