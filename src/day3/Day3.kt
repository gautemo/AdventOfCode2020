package day3

import shared.getLines

fun countTrees(map: List<String>, player: Player): Int{
    var trees = 0
    val mapWidth = map[0].length
    while (player.y < map.size){
        if(map[player.y][player.x % mapWidth] == '#'){
            trees++
        }
        player.move()
    }
    return trees
}

fun multiplyListedSlopeTrees(map: List<String>): Long{
    val slope1 = countTrees(map, Player(1, 1))
    val slope2 = countTrees(map, Player(3, 1))
    val slope3 = countTrees(map, Player(5, 1))
    val slope4 = countTrees(map, Player(7, 1))
    val slope5 = countTrees(map, Player(1, 2))
    return slope1.toLong() * slope2.toLong() * slope3.toLong() * slope4.toLong() * slope5.toLong()
}

class Player(private val moveX: Int, private val moveY: Int){
    var x = 0
    var y = 0

    fun move(){
        x += moveX
        y += moveY
    }
}

fun main(){
    val lines = getLines("day3.txt")
    val player = Player(3, 1)
    val trees = countTrees(lines, player)
    println(trees)
    val multipliedSlopes = multiplyListedSlopeTrees(lines)
    println(multipliedSlopes)
}