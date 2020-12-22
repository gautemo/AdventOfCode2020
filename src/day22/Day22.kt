package day22

import shared.getLines

fun combat(input: List<String>): Int{
    val (player1, player2) = mapPlayers(input)
    while (player1.isNotEmpty() && player2.isNotEmpty()){
        if(player1.first() > player2.first()){
            player1.add(player1.removeAt(0))
            player1.add(player2.removeAt(0))
        }else{
            player2.add(player2.removeAt(0))
            player2.add(player1.removeAt(0))
        }
    }
    val winner = if(player1.size > 0) player1 else player2
    return winner.withIndex().sumBy { (i, card) -> (winner.size - i) * card }
}

fun recursiveCombat(input: List<String>): Int{
    val (player1, player2) = playGame(mapPlayers(input))
    val winner = if(player1.size > 0) player1 else player2
    return winner.withIndex().sumBy { (i, card) -> (winner.size - i) * card }
}

fun playGame(players: List<MutableList<Int>>): List<MutableList<Int>>{
    val history = mutableListOf<String>()
    val (player1, player2) = players
    while (player1.isNotEmpty() && player2.isNotEmpty()){
        if(history.contains("${player1.joinToString()}:${player2.joinToString()}")){
            player2.clear()
            break
        }
        history.add("${player1.joinToString()}:${player2.joinToString()}")
        val cardPlayer1 = player1.removeAt(0)
        val cardPlayer2 = player2.removeAt(0)
        var player1Wins = cardPlayer1 > cardPlayer2
        if(cardPlayer1 <= player1.size && cardPlayer2 <= player2.size){
            player1Wins = playGame(listOf(player1.take(cardPlayer1).toMutableList(), player2.take(cardPlayer2).toMutableList()))[0].size > 0
        }
        if(player1Wins){
            player1.add(cardPlayer1)
            player1.add(cardPlayer2)
        }else{
            player2.add(cardPlayer2)
            player2.add(cardPlayer1)
        }
    }
    return listOf(player1, player2)
}

fun mapPlayers(input: List<String>): List<MutableList<Int>>{
    val players = listOf(
            mutableListOf<Int>(),
            mutableListOf()
    )
    var index = 0
    for(line in input){
        when(line){
            "Player 1:" -> continue
            "Player 2:" -> index = 1
            "" -> continue
            else -> players[index].add(line.toInt())
        }
    }
    return players
}

fun main(){
    val input = getLines("day22.txt")
    val result = combat(input)
    println(result)
    val result2 = recursiveCombat(input)
    println(result2)
}