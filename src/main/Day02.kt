import kotlin.math.abs


fun countSafeReports(reports: List<List<Int>>): Int {
    return reports.count { isSafe(it) };
}

fun isSafe(report: List<Int>): Boolean {

    val levels = report.windowed(2)
    val isReportIncreasing= increasing(report)

    for(pair in levels) {
        val diff =  abs(pair.first() - pair.last())
        if(isReportIncreasing && !increasing(pair)) return false
        if(!isReportIncreasing && increasing(pair)) return false
        if(diff !in 1..3) return false
    }

    return true
}

private fun increasing(pair: List<Int>) = pair.first() < pair.last()

class Day02 {

    fun part1(input: List<String>): Int {

        return countSafeReports(input.map { it.split(" ").map(String::toInt) })
    }
}

fun main() {

    val day01 = Day02()

    val input = readInput("Day02")
    day01.part1(input).println()
    //day01.part2(input).println()
}
