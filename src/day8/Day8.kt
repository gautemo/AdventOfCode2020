package day8

import shared.getLines

fun finAccOnLoop(lines: List<String>): Int{
    return runOperations(lines).acc
}

fun finAccOnFixedLoop(lines: List<String>): Int{
    var lastChanged = -1
    while (true){
        val changedLines = lines.toMutableList()
        val toChange = changedLines.withIndex().indexOfFirst { (i, line) -> i > lastChanged && listOf("jmp", "nop").contains(line.split(" ")[0]) }
        lastChanged = toChange
        changedLines[toChange] = if(changedLines[toChange].contains("jmp")) {
            changedLines[toChange].replace("jmp", "nop")
        }else {
            changedLines[toChange].replace("nop", "jmp")
        }

        val state = runOperations(changedLines)
        if(!state.looped) return state.acc
    }
}

fun runOperations(lines: List<String>): GameState{
    val runnedIndexes = mutableListOf<Int>()
    var index = 0
    val state = GameState()
    while(!runnedIndexes.contains(index)){
        runnedIndexes.add(index)
        val (command, number) = lines[index].split(" ")
        when(command){
            "acc" -> {
                state.acc += number.toInt()
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
            return state
        }
    }
    state.looped = true
    return state
}

data class GameState(var looped: Boolean = false, var acc: Int = 0)

fun main(){
    val lines = getLines("day8.txt")
    val acc = finAccOnLoop(lines)
    println(acc)
    val accOnFix = finAccOnFixedLoop(lines)
    println(accOnFix)
}