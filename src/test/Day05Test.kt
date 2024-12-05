import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Day05Test {

    val pagesOrderRules = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13
        """.trimIndent().split("\n")

    @Test
    fun `pages are in the correct order`() {

        assertTrue(pagesAreOrdered(listOf(75,47,61,53,29), pagesOrderRules))
    }

    @Test
    fun `pages are NOT in the correct order`() {

        assertFalse(pagesAreOrdered(listOf(75,97,47,61,53), pagesOrderRules))
    }

    @Test
    fun `sort pages based on rules`() {

        assertEquals(listOf(97,75,47,61,53), sortPages(listOf(75,97,47,61,53), pagesOrderRules))
        assertEquals(listOf(61,29,13), sortPages(listOf(61,13,29), pagesOrderRules))
        assertEquals(listOf(97,75,47,29,13), sortPages(listOf(97,13,75,29,47), pagesOrderRules))
    }


    @Test
    fun `Day05 part 1`() {
        val input = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
        """.trimIndent().split("\n")

        val day05 = Day05();

        assertEquals(143, day05.part1(input))
    }

    @Test
    fun `Day05 part 2`() {
        val input = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
        """.trimIndent().split("\n")

        val day05 = Day05();

        assertEquals(123, day05.part2(input))
    }
}