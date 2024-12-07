class Day07{
    fun part1(input: List<String>): Long {
        val combinationsGenerator = CombinationsGenerator(listOf("+","*"))
        return input.map { parseEquation(it) }
            .filter { allPossibleResults(it.second, combinationsGenerator).contains(it.first) }
            .sumOf { it.first }
    }

    fun part2(input: List<String>): Long {
        val combinationsGenerator = CombinationsGenerator(listOf("+","*","||"))
        return input.map { parseEquation(it) }
            .filter { allPossibleResults(it.second, combinationsGenerator).contains(it.first) }
            .sumOf { it.first }
    }

}

fun parseEquation(input: String): Pair<Long, List<Int>> {
    val (test, nums) = input.split(": ")
    return test.toLong() to nums.split(" ").map(String::toInt)
}

fun allPossibleResults(nums: List<Int>, generator: CombinationsGenerator): List<Long> {
    val opCombinations = generator.combinations(nums.size - 1)
    val allResults = mutableListOf<Long>()

    for (ops in opCombinations) {
        var res: Long = nums[0].toLong()
        ops.forEachIndexed { index, op ->
            when (op) {
                "+" -> res += nums[index + 1]
                "||" -> res = "$res${nums[index + 1]}".toLong()
                else -> res *= nums[index + 1]
            }
        }

        allResults.add(res)
    }

    return allResults
}

class CombinationsGenerator(val values: List<String>) {

    fun combinations(length: Int): List<List<String>> {
        val combinations = mutableListOf<List<String>>()

        combinations.addAll(generateCombination(length, emptyList()))

        return combinations

    }

    private fun generateCombination(length: Int, combination: List<String>):  List<List<String>>{
        val combinations = mutableListOf<List<String>>()
        if(length == 0) return combinations

        if(combination.size == length) {
            combinations.add(combination)
            return combinations
        }

        for(op in values) {
            combinations.addAll(generateCombination(length, combination + op))
        }

        return combinations
    }
}

fun main() {
    val day07 = Day07()

    val input = readInput("Day07")
    val startTime = System.currentTimeMillis()
    day07.part1(input).println()
    day07.part2(input).println()
    val endTime = System.currentTimeMillis()
    val elapsedTimeInSeconds = (endTime - startTime) / 1000.0
    println("Time: $elapsedTimeInSeconds second")
}