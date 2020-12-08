package day8

import shared.getLines

fun finAccOnLoop(lines: List<String>): Int{
    val runnedIndexes = mutableListOf<Int>()
    var index = 0
    var acc = 0
    while(!runnedIndexes.contains(index)){
        runnedIndexes.add(index)
        val (command, number) = lines[index].split(" ")
        when(command){
            "acc" -> {
                acc += number.toInt()
                index++
            }
            "jmp" -> {
                index += number.toInt()
            }
            else -> {
                index++
            }
        }
    }
    return acc
}

// TODO clean up
fun finAccOnFixedLoop(lines: List<String>): Int{
    var lastChanged = -1
    while (true){
        var changedLines = lines.toMutableList()
        val toChange = changedLines.withIndex().indexOfFirst { (i, line) -> i > lastChanged && line.split(" ")[0] == "jmp" }
        lastChanged = toChange
        changedLines[toChange] = changedLines[toChange].replace("jmp", "nop")

        val runnedIndexes = mutableListOf<Int>()
        var index = 0
        var acc = 0
        while(!runnedIndexes.contains(index)){
            runnedIndexes.add(index)
            val (command, number) = changedLines[index].split(" ")
            when(command){
                "acc" -> {
                    acc += number.toInt()
                    index++
                }
                "jmp" -> {
                    index += number.toInt()
                }
                else -> {
                    index++
                }
            }
            if(index == lines.size){
                return acc
            }
        }
    }
}

fun main(){
    val lines = getLines("day8.txt")
    val acc = finAccOnLoop(lines)
    println(acc)
    val accOnFix = finAccOnFixedLoop(lines)
    println(accOnFix)
}