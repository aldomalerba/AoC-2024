import kotlin.math.abs

fun main() {

    fun day01Parser(input: List<String>) = input.map {
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

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)


    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
