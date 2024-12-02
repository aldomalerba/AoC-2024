import kotlin.math.abs

class Day01 {

    private fun inputParser(input: List<String>) = input.map {
        val (left, right) = it.split(" ").filter { it != "" }
        left.toInt() to right.toInt()
    }.unzip()


    fun part1(input: List<String>): Int {
        val (leftSide, rightSide) = inputParser(input)

        val pairs = leftSide.sorted().zip(rightSide.sorted())
        val sizes = pairs.map { pair -> abs(pair.first - pair.second) }

        return sizes.sum()
    }

    fun part2(input: List<String>): Int {
        val (firstSide, secondSide) = inputParser(input)

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
