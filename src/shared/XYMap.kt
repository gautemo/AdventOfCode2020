package shared

class XYMap(input: String) {
    val width = input.lines().getOrElse(0){""}.length
    val height = input.lines().size
    private val xyMap = mutableMapOf<Point, Char>()
    val map: String
        get(){
            var buildMap = ""
            for(y in 0 until height){
                for(x in 0 until width){
                    buildMap += xyMap[Point(x, y)]
                }
                if(y != height - 1) buildMap += '\n'
            }
            return buildMap
        }

    init {
        for((y, row) in input.lines().withIndex()) {
            for ((x, char) in row.withIndex()) {
                xyMap[Point(x, y)] = char
            }
        }
    }

    fun getAt(x: Int, y: Int) = xyMap[Point(x, y)]

    fun setAt(x: Int, y: Int, char: Char) {
        xyMap[Point(x, y)] = char
    }

    fun countAdjacent(x: Int, y: Int, charToCount: Char): Int{
        var adjacentTaken = 0
        if(xyMap[Point(x-1, y-1)] == charToCount) adjacentTaken++
        if(xyMap[Point(x-1, y)] == charToCount) adjacentTaken++
        if(xyMap[Point(x-1, y+1)] == charToCount) adjacentTaken++
        if(xyMap[Point(x, y-1)] == charToCount) adjacentTaken++
        if(xyMap[Point(x, y+1)] == charToCount) adjacentTaken++
        if(xyMap[Point(x+1, y-1)] == charToCount) adjacentTaken++
        if(xyMap[Point(x+1, y)] == charToCount) adjacentTaken++
        if(xyMap[Point(x+1, y+1)] == charToCount) adjacentTaken++
        return adjacentTaken
    }

    fun countChar(charToCount: Char): Int{
        var count = 0
        for(y in 0 until height){
            for(x in 0 until width) {
                if(xyMap[Point(x,y)] == charToCount) count++
            }
        }
        return count
    }
}

data class Point(val x: Int, val y: Int)