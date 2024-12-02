import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day02Test {

    @Test
    fun `report is safe with all decreasing levels`() {
        val report = Report(listOf(7,6))

        val isSafe = report.isSafe()

        assertTrue(isSafe)
    }

    @Test
    fun `report is safe with all increasing levels`() {
        val report = Report(listOf(6,7,8))

        val isSafe = report.isSafe()

        assertTrue(isSafe)
    }

    @Test
    fun `report is not safe if not all levels are decreasing`() {
        val report = Report(listOf(7,4,5))

        val isSafe = report.isSafe()

        assertFalse(isSafe)
    }

    @Test
    fun `report is not safe  Report( not all levels are increasing`() {
        val report = Report(listOf(3,4,2))

        val isSafe = report.isSafe()

        assertFalse(isSafe)
    }

    @Test
    fun `report is not safe with two adjacent equals levels`() {
        val report = Report(listOf(7,7))

        val isSafe = report.isSafe()

        assertFalse(isSafe)
    }

    @Test
    fun `report is not safe with two adjacent levels with diff more than three`() {
        val report = Report(listOf(7,1))

        val isSafe = report.isSafe()

        assertFalse(isSafe)
    }

    @Test
    fun `report is safe with a tolerate of a single bad level`() {
        val report = Report("1 2 3 4 5 5".split(" ").map(String::toInt))
        report.tolerate = 1

        assertTrue(report.isSafe())
    }

    @Test
    fun `report is unsafe with a tolerate of a single bad level`() {
        val report = Report(listOf(9, 7, 6, 2, 1))
        report.tolerate = 1

        assertFalse(report.isSafe())
    }

    @Test
    fun `count safe reports`() {
        val safe = Report(listOf(7,6,4,2,1))
        val unsafe = Report(listOf(1, 2, 7, 8, 9))

        assertEquals(1, countSafeReports(listOf(safe, unsafe)))
    }

    @Test
    fun `count safe reports with a tolerate`() {
        val safe = Report(listOf(7,6,4,2,1))
        val unsafe = Report(listOf(1, 2, 7, 8, 9))

        assertEquals(1, countSafeReports(listOf(safe, unsafe)))
    }

    @Test
    fun `Day02 part 1`() {
         val testInput = """
             7 6 4 2 1
             1 2 7 8 9
             9 7 6 2 1
             1 3 2 4 5
             8 6 4 4 1
             1 3 6 7 9
         """.trimIndent().split("\n")

        val day02 = Day02()

        val result = day02.part1(testInput)

        assertEquals(2, result)
    }

    @Test
    fun `Day02 part 2`() {
        val testInput = """
             7 6 4 2 1
             1 2 7 8 9
             9 7 6 2 1
             1 3 2 4 5
             8 6 4 4 1
             1 3 6 7 9
         """.trimIndent().split("\n")

        val day02 = Day02()

        val result = day02.part2(testInput)

        assertEquals(4, result)
    }
}