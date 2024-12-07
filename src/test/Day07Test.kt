import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day07Test {

    val combinationsGenerator = CombinationsGenerator(listOf("+", "*"))

    @Test
    fun `get all combinations`() {
        val combinations = combinationsGenerator.combinations(0)

        assertEquals(emptyList<Char>(), combinations)
    }

    @Test
    fun `get all combinations with length 1`() {
        val combinations = combinationsGenerator.combinations(1)

        assertEquals(listOf(listOf("+"), listOf("*")), combinations)
    }

    @Test
    fun `get all combinations with length 2`() {
        val combinations = combinationsGenerator.combinations(2)

        assertEquals(listOf(
            listOf("+","+"),
            listOf("+","*"),
            listOf("*","+"),
            listOf("*","*"),
        ), combinations)
    }

    @Test
    fun `get all combinations with length 3`() {
        val combinations = combinationsGenerator.combinations(3)

        assertEquals(listOf(
            listOf("+","+","+"),
            listOf("+","+","*"),
            listOf("+","*","+"),
            listOf("+","*","*"),
            listOf("*","+","+"),
            listOf("*","+","*"),
            listOf("*","*","+"),
            listOf("*","*","*"),
        ), combinations)
    }

    @Test
    fun `all possible results with operators plus and mul`() {
        val nums = listOf(10, 19)

        assertEquals(listOf(29L, 190L),  allPossibleResults(nums, combinationsGenerator))
    }

    @Test
    fun `all possible results with operators plus and mul and concatenation`() {
        val combinationsGenerator = CombinationsGenerator(listOf("+", "*", "||"))

        val nums = listOf(15, 6)

        assertEquals(listOf(21L, 90L, 156L),  allPossibleResults(nums, combinationsGenerator))
    }

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
        val input = "7290: 6 8 6 15"
        val equation = parseEquation(input)
        assertEquals(Pair(7290L, listOf(6, 8, 6, 15)), equation)
    }

}