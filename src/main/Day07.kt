class Day07{
    fun part1(input: List<String>): Long {
        val operations = listOf("+","*")
        return input.map(String::toEquation)
            .filter { it.containsValidCombination(operations) }
            .sumOf { it.first }
    }

    fun part2(input: List<String>): Long {
        val operations = listOf("+","*","||")
        return input.map(String::toEquation)
            .filter { it.containsValidCombination(operations) }
            .sumOf { it.first }
    }

    private fun Pair<Long, List<Long>>.containsValidCombination(operations: List<String>): Boolean {
        val (test, nums) = this
        return operations.any { isValidRecursive(test to nums.drop(1), operations, it, nums.first()) }
    }

    private fun isValidRecursive(equation: Pair<Long, List<Long>>, operations: List<String>, op: String ,sum: Long = 0L): Boolean {
        val (test, nums) = equation
        if(sum == test) return true;
        if(nums.isEmpty() || sum > test) return false

        val first = equation.second.first()
        val res = when (op) {
            "+" -> sum + first
            "||" -> "$sum${first}".toLong()
            else -> sum * first
        }

        return operations.any { isValidRecursive(test to nums.drop(1), operations, it, res) }
    }

}

fun String.toEquation(): Pair<Long, List<Long>> {
    val (test, nums) = this.split(": ")
    return test.toLong() to nums.split(" ").map(String::toLong)
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