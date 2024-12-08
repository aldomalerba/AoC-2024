class Day08: Day {
    override fun part1(input: List<String>): Int {
        val antennaToPositions = parseInput(input)
        val height = input.size
        val length = input.first().length

        val allAntinodes = antennaToPositions.map { antinodes(it.value, height, length, false) }.flatten()

        return allAntinodes.toSet().size
    }

    override fun part2(input: List<String>): Int {
        val antennaToPositions = parseInput(input)
        val height = input.size
        val length = input.first().length

        val allAntinodes = antennaToPositions.map { antinodes(it.value, height, length, true) }.flatten()

        return allAntinodes.toSet().size
    }
}

fun main() {
    val day08 = Day08()

    val input = readInput("Day08")
    val startTime = System.currentTimeMillis()
    day08.part1(input).println()
    day08.part2(input).println()
    val endTime = System.currentTimeMillis()
    val elapsedTimeInSeconds = (endTime - startTime) / 1000.0
    println("Time: $elapsedTimeInSeconds second")
}

fun parseInput(input: List<String>): Map<Char, List<Pair<Int,Int>>> {
    val antennas = mutableMapOf<Char,List<Pair<Int, Int>>>()
    for ((y, row) in input.withIndex()){
        for ((x, char) in row.withIndex()) {
            if(char != '.'){
                var antennaPositions = listOf<Pair<Int,Int>>()
                if(antennas.containsKey(char)){
                    antennaPositions = antennas.get(char)!!
                }
                antennas[char] = antennaPositions + (x to y)
            }
        }
    }

    return antennas
}


fun antinodes(antennas: List<Pair<Int, Int>>, length: Int, height: Int, extended: Boolean = false): List<Pair<Int,Int>> {
    val allAntinodes = mutableListOf<Pair<Int,Int>>()

    for ((i, antenna) in antennas.withIndex()) {
        for(nextAntenna in antennas.drop(i+1)) {
            allAntinodes.addAll(antinodesOf(antenna, nextAntenna, length, height, extended))
        }
    }

    return allAntinodes
}

fun antinodesOf(
    first: Pair<Int, Int>,
    second: Pair<Int, Int>,
    length: Int,
    height: Int,
    extended: Boolean,
): List<Pair<Int, Int>> {
    val antinodes = mutableListOf<Pair<Int, Int>>()
    var (firstX, firstY) = first
    var (secondX, secondY) = second

    if(extended) {
        antinodes.add(first)
        antinodes.add(second)
    }

    val diffX = firstX - second.first
    val diffY = firstY - second.second


    do {
        firstX += diffX
        firstY += diffY
        val inside = isInside((firstX to firstY), length, height)
        if(inside) antinodes.add(firstX to firstY)
    }while (extended && inside)


    do {
        secondX -= diffX
        secondY -= diffY
        val inside = isInside((secondX to secondY), length, height)
        if(inside) antinodes.add(secondX to secondY)
    }while (extended && inside)

    return antinodes
}

private fun isInside(first: Pair<Int, Int>, height: Int, length: Int) =
    first.first >= 0 && first.second >= 0 && first.second < height && first.first < length