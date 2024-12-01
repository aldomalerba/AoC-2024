import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day01Test {

    private val day01 = Day01()

    private val testInput = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
        """.trimIndent().split("\n")

    @Test
    fun part1() {
        val result = day01.part1(testInput);
        assertEquals(11,result)
    }

    @Test
    fun part2() {
        val result = day01.part2(testInput);
        assertEquals(31,result)
    }
}