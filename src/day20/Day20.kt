package day20

import shared.Point
import shared.getText
import shared.separateByBlankLine
import kotlin.math.sqrt

fun cornerTilesMultiplied(map: TileMap): Long{
    return map.xy[Point(0,0)]!!.id * map.xy[Point(map.length-1,0)]!!.id * map.xy[Point(map.length-1,map.length-1)]!!.id * map.xy[Point(0,map.length-1)]!!.id
}

fun findImage(input: String): TileMap{
    val tiles = toTiles(input)
    val length = sqrt(tiles.size.toDouble()).toInt()
    val tileCombinations = tiles.flatMap { allTileCombinations(it) }
    val map = TileMap(length)
    val testedCombinations = mutableListOf<Map<Point, Tile>>()
    while(!map.isFull()){
        places@for(y in 0 until map.length){
            for(x in 0 until map.length){
                val toTry = tileCombinations.find {
                    val xyNow = map.xy.toMutableMap()
                    xyNow[Point(x,y)] = it
                    !map.xy.any { (_, t) -> t.id == it.id } && !testedCombinations.contains(xyNow) && it.fits(map.xy[Point(x-1, y)], map.xy[Point(x, y-1)])
                }
                if(toTry != null){
                    map.add(Point(x,y), toTry)
                }else{
                    testedCombinations.add(map.xy.toMap())
                    map.wipe()
                    break@places
                }
            }
        }
    }
    return map
}

fun allTileCombinations(tile: Tile): List<Tile>{
    return listOf(
            tile,
            Tile(tile.input, 0, true),
            Tile(tile.input, 1),
            Tile(tile.input, 1, true),
            Tile(tile.input, 2),
            Tile(tile.input, 2, true),
            Tile(tile.input, 3),
            Tile(tile.input, 3, true)
    )
}

fun toTiles(input: String) = separateByBlankLine(input).map { Tile(it) }

data class Tile(val input: String, var rotated: Int = 0, var flipped: Boolean = false){
    val id = input.lines()[0].split(" ")[1].trimEnd(':').toLong()
    val pixels: String

    init {
        var rotatedFlippedPixels = input.replace(input.lines()[0] + "\n", "")
        repeat(rotated){
            var newRotated = ""
            for(x in rotatedFlippedPixels.lines().indices){
                for(y in rotatedFlippedPixels.lines().size - 1 downTo 0){
                    newRotated += rotatedFlippedPixels.lines()[y][x]
                }
                if(x < rotatedFlippedPixels.lines().size - 1) {
                    newRotated += "\n"
                }
            }
            rotatedFlippedPixels = newRotated
        }
        if(flipped){
            var newFlipped = ""
            for((i, line) in rotatedFlippedPixels.lines().withIndex()){
                newFlipped += line.reversed()
                if(i < rotatedFlippedPixels.lines().size - 1) {
                    newFlipped += "\n"
                }
            }
            rotatedFlippedPixels = newFlipped
        }
        pixels = rotatedFlippedPixels
    }

    fun fits(left: Tile?, up: Tile?) = (left == null || left.right() == left()) && (up == null || up.down() == up())

    fun up() = pixels.lines()[0]

    fun down() = pixels.lines().last()

    fun left() = pixels.lines().map { it.first() }.joinToString("")

    fun right() = pixels.lines().map { it.last() }.joinToString("")

    fun pixelsWithoutFrame(): String{
        var img = ""
        for((i, line) in pixels.lines().withIndex()){
            if(i != 0 && i != pixels.lines().size - 1){
                img += line.substring(1, line.length-1)
                if(i < pixels.lines().size - 2) {
                    img += "\n"
                }
            }
        }
        return img
    }
}

class TileMap(val length: Int){
    val xy = mutableMapOf<Point, Tile>()

    fun add(point: Point, tile: Tile){
        xy[point] = tile
    }

    fun wipe(){
        xy.clear()
    }

    fun isFull() = xy.size == length * length

    fun getImage(): String{
        val tileHeight = xy[Point(0,0)]!!.pixelsWithoutFrame().lines().size
        val img = MutableList(length * tileHeight){""}
        for(y in 0 until length){
            for(x in 0 until length){
                for((i, line) in xy[Point(x,y)]!!.pixelsWithoutFrame().lines().withIndex()){
                    img[y*tileHeight+i] += line
                }
            }
        }
        return img.joinToString("\n")
    }
}

fun main(){
    val input = getText("day20.txt")
    val map = findImage(input)
    val result = cornerTilesMultiplied(map)
    println(result)
    val waterRoughness = waterRoughness(map)
    println(waterRoughness)
}

fun monsterPattern(): List<Point>{
    val monster = """
                  # 
#    ##    ##    ###
 #  #  #  #  #  #   
    """.lines().filter { it.isNotBlank() }
    val points = mutableListOf<Point>()
    for(y in monster.indices) {
        for(x in monster[0].indices){
            if(monster[y][x] == '#'){
                points.add(Point(x,y))
            }
        }
    }
    return points
}

fun countMonsters(img: String): Int{
    var count = 0
    val monsterPoints = monsterPattern()
    val monsterWidth = monsterPoints.map { it.x }.max()!!
    val monsterHeight = monsterPoints.map { it.y }.max()!!
    for(y in 0 until img.lines().size - monsterHeight){
        for(x in 0 until img.lines().size - monsterWidth){
            val found = monsterPattern().all { img.lines()[it.y + y][it.x + x] == '#' }
            if(found) count++
        }
    }
    return count
}

fun waterRoughness(map: TileMap): Int{
    val img = map.getImage()
    val tileCombinations = allTileCombinations(Tile("Complete 1:\n$img"))
    val maxMonstersFound = tileCombinations.map { countMonsters(it.pixels) }.max()!!

    return img.count { it == '#' } - monsterPattern().size * maxMonstersFound
}