import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day04Test {

    @Test
    fun `horizontal search from index 0 the word is not XMAS`() {
        val input = """
            MMMSXXMASM
        """.trimIndent().split("\n")

        val result = horizontal(input, 0, 0, word = "XMAS")

        assertFalse(result)
    }

    @Test
    fun `horizontal search from index 4 the word is XMAS`() {
        val input = """
            MMMSXXMASM
        """.trimIndent().split("\n")

        val result = horizontal(input, 5, word = "XMAS")

        assertEquals(true, result)
    }

    @Test
    fun `horizontal search from index 4 reversed the word is SAMX`() {
        val input = """
            MMMSXSAMXM
        """.trimIndent().split("\n")

        val result = horizontal(input, 5, word = "XMAS")

        assertEquals(true, result)
    }

    @Test
    fun `horizontal search from index 1, and rowIndex 1 find the worD`() {
        val input = """
            XXXXXXXXXX
            XSAMXXXXXX
        """.trimIndent().split("\n")

        val result = horizontal(input, 1, 1, "XMAS")

        assertEquals(true, result)
    }

    @Test
    fun `vertical search from colIndex 0 and rowIndex 0 is XMAS`() {
        val input = """
            X
            M
            A
            S
        """.trimIndent().split("\n")

        val result = vertical(input, rowIndex = 0, colIndex = 0, word = "XMAS")

        assertTrue(result)
    }

    @Test
    fun `vertical search reversed from colIndex 0 and rowIndex 0 is XMAS`() {
        val input = """
            S
            A
            M
            X
        """.trimIndent().split("\n")

        val result = vertical(input, rowIndex = 0, word = "XMAS")

        assertTrue(result)
    }

    @Test
    fun `vertical search from colIndex 1 and rowIndex 0 is word`() {
        val input = """
            XS
            XA
            XM
            XX
        """.trimIndent().split("\n")

        val result = vertical(input, 1, 0, "XMAS")

        assertTrue(result)
    }

    @Test
    fun `vertical search from colIndex 1 and rowIndex 1 is word`() {
        val input = """
            XX
            XS
            XA
            XM
            XX
        """.trimIndent().split("\n")

        val result = vertical(input, 1, 1, "XMAS")

        assertTrue(result)
    }

    @Test
    fun `vertical search from colIndex 1 and rowIndex too big`() {
        val input = """
            XX
            XS
            XA
            XM
            XX
        """.trimIndent().split("\n")

        val result = vertical(input, 1, 3, "XMAS")

        assertFalse(result)
    }

    @Test
    fun `diagonal right search from colIndex 0 and rowIndex 0`() {
        val input = """
            XSXX
            XMXX
            XMAA
            XXXS
        """.trimIndent().split("\n")

        val result = diagonalRight(input, word = "XMAS")

        assertTrue(result)
    }

    @Test
    fun `diagonal right search reversed from colIndex 0 and rowIndex 0`() {
        val input = """
            SSXX
            XAXX
            XMMA
            XXXX
        """.trimIndent().split("\n")

        val result = diagonalRight(input, 0 , 0, word = "XMAS")

        assertTrue(result)
    }

    @Test
    fun `diagonal left search from colIndex 3 and rowIndex 0`() {
        val input = """
            SSXX
            XAMX
            XAMA
            SXXX
        """.trimIndent().split("\n")

        val result = diagonalLeft(input, 3, 0, "XMAS")

        assertTrue(result)
    }

    @Test
    fun `diagonal right search reversed from colIndex 1 and rowIndex 1`() {
        val input = """
            XXXXX
            XSSXX
            XXAXX
            XXMMA
            XXXXX
        """.trimIndent().split("\n")

        val result = diagonalRight(input, 1, 1, "XMAS")

        assertTrue(result)
    }

    @Test
    fun `Day04 with only horizontal words`() {
        val input = """
            XXXXXXMASX
            XSAMXXXXXX
        """.trimIndent().split("\n")

        val result = Day04().part1(input)

        assertEquals(2, result)
    }

    @Test
    fun `Day04 with only vertical words`() {
        val input = """
            XX
            MS
            AA
            SM
            XX
        """.trimIndent().split("\n")

        val result = Day04().part1(input)

        assertEquals(2, result)
    }

    @Test
    fun `Day04 part 1`() {
        val input = """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
        """.trimIndent().split("\n")

        val result = Day04().part1(input)

        assertEquals(18, result)
    }

    @Test
    fun `Day04 part 2`() {
        val input = """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
        """.trimIndent().split("\n")

        val result = Day04().part2(input)

        assertEquals(9, result)
    }
}