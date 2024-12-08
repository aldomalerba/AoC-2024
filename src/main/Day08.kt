class Day08: Day {
    override fun part1(input: List<String>): Int {
        val antennaToPositions = parseInput(input)
        val allAntinodes = antennaToPositions.map { antinodes(it.value) }.flatten()
        val antinodesInMap =  allAntinodes.filter { isInside(it, input.size, input.first().length) }
        return antinodesInMap.toSet().size
    }

    override fun part2(input: List<String>): Int {
        val antennaToPositions = parseInput(input)
        val allAntinodes = antennaToPositions.map { antinodesExtended(it.value, input.size, input.first().length) }.flatten()
        val antinodesInMap =  allAntinodes.filter { isInside(it, input.size, input.first().length) }
        return antinodesInMap.toSet().size
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

fun antinodes(antennas: List<Pair<Int, Int>>): List<Pair<Int, Int>> {

    val antinodes = mutableListOf<Pair<Int,Int>>()

    for ((i, antenna) in antennas.withIndex()) {
        for(nextAntenna in antennas.drop(i+1)) {
            antinodes.addAll(antinodesOf(antenna, nextAntenna))
        }
    }

    return antinodes
}


fun antinodesExtended(antennas: List<Pair<Int, Int>>, length: Int, height: Int): List<Pair<Int,Int>> {
    val allAntinodes = mutableListOf<Pair<Int,Int>>()

    allAntinodes.addAll(antennas)

    for ((i, antenna) in antennas.withIndex()) {
        for(nextAntenna in antennas.drop(i+1)) {
            allAntinodes.addAll(antinodesExtendedOf(antenna, nextAntenna, length, height))
        }
    }

    return allAntinodes
}

fun antinodesExtendedOf(
    first: Pair<Int, Int>,
    second: Pair<Int, Int>,
    length: Int,
    height: Int,
): List<Pair<Int, Int>> {
    val antinodes = mutableListOf<Pair<Int, Int>>()
    var (firstX, firstY) = first
    var (secondX, secondY) = second

    val diffX = firstX - second.first
    val diffY = firstY - second.second

    do {
        firstX += diffX
        firstY += diffY
        antinodes.add(firstX to firstY)
    }while (isInside((firstX to firstY), length, height))


    do {
        secondX -= diffX
        secondY -= diffY
        antinodes.add(secondX to secondY)
    }while (isInside((secondX to secondY), length, height))

    return antinodes
}

private fun isInside(first: Pair<Int, Int>, height: Int, length: Int) =
    first.first >= 0 && first.second >= 0 && first.second < height && first.first < length

fun antinodesOf(first: Pair<Int,Int>, second: Pair<Int, Int>): List<Pair<Int, Int>> {
    val diffX = first.first - second.first
    val diffY = first.second - second.second

    val firstX = first.first + diffX
    val firstY = first.second + diffY

    val secondX = second.first - diffX
    val secondY = second.second - diffY

    return listOf(firstX to firstY, secondX to secondY)
}