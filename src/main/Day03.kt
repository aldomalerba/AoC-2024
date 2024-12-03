fun instructions(corruptedInput: String): List<String> {
    val findAll = Regex("mul[(](\\d+),(\\d+)[)]").findAll(corruptedInput)
    return findAll.map { it.value }.toList()
}

fun execute(instruction: String): Int {
    val numbers = Regex("(\\d+)").findAll(instruction).map{ it.value.toInt() }.toList()
    return numbers.first() * numbers.last()
}


class Day03 : Day{
    override fun part1(input: List<String>): Int {
        return instructions(input.joinToString()).sumOf { execute(it) }
    }

    override fun part2(input: List<String>): Int {
        TODO("Not yet implemented")
    }

}

fun main() {
    val day03 = Day03()

    val input = readInput("Day03")
    day03.part1(input).println()
    //day03.part2(input).println()
}
