package shared

class DimensionMap(input: String, private val dimension: Int) {
    private var dMap = mutableMapOf<PointD, Char>()
    var ranges = mutableMapOf<Int, IntRange>()

    init {
        for(i in 0 until dimension){
            ranges[i] = -1..1
        }
        for((y, row) in input.lines().withIndex()) {
            for ((x, char) in row.withIndex()) {
                setAt(char, x, y, *List(dimension - 2) { 0 }.toIntArray())
            }
        }
    }

    constructor(initMap: Map<PointD, Char>, initRanges: Map<Int, IntRange>) : this("", initRanges.size){
        dMap = initMap.toMutableMap()
        ranges = initRanges.toMutableMap()
    }

    fun copy(): DimensionMap{
        return DimensionMap(dMap, ranges)
    }

    fun getAt(vararg points: Int) = dMap[PointD(points.asList())]

    fun setAt(char: Char, vararg points: Int) {
        dMap[PointD(points.asList())] = char
        for(i in 0 until dimension){
            if(points[i] <= ranges[i]!!.first) ranges[i] = points[i]-1..ranges[i]!!.last
            if(points[i] >= ranges[i]!!.last) ranges[i] = ranges[i]!!.first..points[i]+1
        }
    }

    fun countAdjacent(charToCount: Char, vararg points: Int): Int{
        fun forPoints(pointsBuild: List<Int>): List<PointD>{
            return when{
                pointsBuild == points.toList() -> listOf()
                pointsBuild.size == ranges.size -> listOf(PointD(pointsBuild))
                else -> {
                    val current = pointsBuild.size
                    forPoints(pointsBuild + (points[current] - 1)) +
                            forPoints(pointsBuild + points[current]) +
                            forPoints(pointsBuild + (points[current] + 1))
                }
            }
        }

        val adjacentPoints = forPoints(listOf())
        return adjacentPoints.count { dMap[it] == charToCount }
    }

    fun countChar(charToCount: Char): Int{
        return dMap.values.count { it == charToCount }
    }
}

data class PointD(val points: List<Int>)