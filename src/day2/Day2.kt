package day2

import shared.getLines

fun main(){
    val lines = getLines("day2.txt")
    val count = findValidPasswords(lines)
    println(count)
    val count2 = findValidPasswords2(lines)
    println(count2)
}

fun findValidPasswords(lines: List<String>): Int{
    return lines.count { isValid(it) }
}

fun isValid(line: String): Boolean{
    val sections = line.split(" ")
    val (min, max) = sections[0].split("-").map { it.toInt() }
    val char = sections[1][0]
    val pw = sections[2]
    val occurs = pw.count { char == it }
    return occurs in min..max
}

fun findValidPasswords2(lines: List<String>): Int{
    return lines.count { isValid2(it) }
}

fun isValid2(line: String): Boolean{
    val sections = line.split(" ")
    val (pos1, pos2) = sections[0].split("-").map { it.toInt() - 1 }
    val char = sections[1][0]
    val pw = sections[2]
    val check1 = pw[pos1] == char
    val check2 = pw[pos2] == char
    return check1 xor check2
}