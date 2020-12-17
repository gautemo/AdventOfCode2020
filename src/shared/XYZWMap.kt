package shared

class XYZWMap(input: String, private val dimension: Int) {
    private var xyzwMap = mutableMapOf<PointXYZW, Char>()
    var xRange = -1..1
    var yRange = -1..1
    var zRange = if(dimension > 2) -1..1 else 0..0
    var wRange = if(dimension > 3) -1..1 else 0..0

    init {
        for((y, row) in input.lines().withIndex()) {
            for ((x, char) in row.withIndex()) {
                setAt(x, y, 0, char = char)
            }
        }
    }

    constructor(initMap: Map<PointXYZW, Char>, initXRange: IntRange, initYRange: IntRange, initZRange: IntRange, initWRange: IntRange, dimension: Int) : this("", dimension){
        xyzwMap = initMap.toMutableMap()
        xRange = initXRange
        yRange = initYRange
        zRange = initZRange
        wRange = initWRange
    }

    fun copy(): XYZWMap{
        return XYZWMap(xyzwMap, xRange, yRange, zRange, wRange, dimension)
    }

    fun getAt(x: Int, y: Int, z: Int, w: Int = 0) = xyzwMap[PointXYZW(x, y, z, w)]

    fun setAt(x: Int, y: Int, z: Int, w: Int = 0, char: Char) {
        xyzwMap[PointXYZW(x, y, z, w)] = char
        if(x <= xRange.first) xRange = x-1..xRange.last
        if(x >= xRange.last) xRange = xRange.first..x+1
        if(y <= yRange.first) yRange = y-1..yRange.last
        if(y >= yRange.last) yRange = yRange.first..y+1
        if(dimension > 2){
            if(z <= zRange.first) zRange = z-1..zRange.last
            if(z >= zRange.last) zRange = zRange.first..z+1
        }
        if(dimension > 3){
            if(w <= wRange.first) wRange = w-1..wRange.last
            if(w >= wRange.last) wRange = wRange.first..w+1
        }
    }

    fun countAdjacent(x: Int, y: Int, z: Int, w: Int = 0, charToCount: Char): Int{
        var adjacentTaken = 0
        for(xIndex in -1..1){
            for(yIndex in -1..1){
                for(zIndex in -1..1){
                    for(wIndex in -1..1){
                        if(xIndex == 0 && yIndex == 0 && zIndex == 0 && wIndex == 0){
                            continue
                        }
                        if(xyzwMap[PointXYZW(x + xIndex, y + yIndex, z + zIndex, w + wIndex)] == charToCount) adjacentTaken++
                    }
                }
            }
        }
        return adjacentTaken
    }

    fun countChar(charToCount: Char): Int{
        return xyzwMap.values.count { it == charToCount }
    }
}

data class PointXYZW(val x: Int, val y: Int, val z: Int, val w: Int)