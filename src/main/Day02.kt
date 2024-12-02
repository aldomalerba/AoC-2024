import kotlin.math.abs


fun countSafeReports(reports: List<Report>): Int {
    return reports.count(Report::isSafe)
}

class Report(private val levels: List<Int>) {

    var tolerate = 0
    private val isIncreasing = increasing(levels.first(), levels.last())

    fun isSafe(): Boolean {
        return isSafe(levels, 0)
    }

    private fun isSafe(levelsToCheck: List<Int>, badLevels: Int): Boolean {
        if (badLevels > tolerate) return false

        levelsToCheck.dropLast(1).forEachIndexed { index, level ->

            val nextLevel = levelsToCheck[index + 1]
            val diff = abs(level - nextLevel)

            val isBadLevel = wrongDirection(level, nextLevel) || diff !in 1..3

            val levelsWithoutCurrentLevel = levelsToCheck.filterIndexed { i, _ -> i != index }
            val levelsWithoutNextLevel = levelsToCheck.filterIndexed { i, _ -> i != index + 1 }

            if(isBadLevel) return  isSafe(levelsWithoutCurrentLevel, badLevels + 1) || isSafe(levelsWithoutNextLevel, badLevels + 1)
        }

        return true;
    }

    private fun wrongDirection(level: Int, nextLevel: Int) = isIncreasing != increasing(level, nextLevel)

    private fun increasing(from: Int, to: Int) = from < to

}

class Day02 {

    private fun parseInput(input: List<String>, tolerate: Int) = input.map {
        val report = Report(it.split(" ").map(String::toInt))
        report.tolerate = tolerate;
        report
    }

    fun part1(input: List<String>): Int {
        val reports = parseInput(input, 0)
        return countSafeReports(reports)
    }


    fun part2(input: List<String>): Int {
        val reports = parseInput(input, 1)
        return countSafeReports(reports)
    }
}

fun main() {

    val day01 = Day02()

    val input = readInput("Day02")
    day01.part1(input).println()
    day01.part2(input).println()
}
