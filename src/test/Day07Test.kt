import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day07Test {
    @Test
    fun `Day07 part 1`() {
        val day07 = Day07()

        val input = """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
        """.trimIndent().split("\n")

        assertEquals(3749, day07.part1(input))
    }

    @Test
    fun `Day07 part 2`() {
        val day07 = Day07()

        val input = """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
        """.trimIndent().split("\n")

        assertEquals(11387, day07.part2(input))
    }

    @Test
    fun `parse an equation`() {
        val equation = "7290: 6 8 6 15".toEquation()
        assertEquals(Pair(7290L, listOf(6L, 8L, 6L, 15L)), equation)
    }

}