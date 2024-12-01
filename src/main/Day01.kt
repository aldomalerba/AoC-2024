import kotlin.math.abs

class Day01 {

    private fun day01Parser(input: List<String>) = input.map {
        val parts = it.split(" ")
        Pair(parts.first().toInt(), parts.last().toInt())
    }.unzip()


    fun part1(input: List<String>): Int {
        val (leftSide, rightSide) = day01Parser(input)

        val pairs = leftSide.sorted().zip(rightSide.sorted())
        val sizes = pairs.map { pair -> abs(pair.first - pair.second) }

        return sizes.sum()
    }

    fun part2(input: List<String>): Int {
        val (firstSide, secondSide) = day01Parser(input)

        val scores = firstSide.map { left -> secondSide.count { it == left} * left }
        return scores.sum()
    }

}
fun main() {

    val day01 = Day01()

    val input = readInput("Day01")
    day01.part1(input).println()
    day01.part2(input).println()
}
