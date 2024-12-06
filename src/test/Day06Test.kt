import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class Day06Test {

    @Test
    fun `Day06 part 01 stuck exception`() {
        val day06 = Day06()
        val input = """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #..#......
            ......#...
        """.trimIndent().split("\n")

        assertThrows<GuardStuckException> { day06.part1(input) }
    }


    @Test
    fun `Day06 part 02`() {
        val input = """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...

        """.trimIndent().split("\n")

        val result = Day06().part2(input)

        assertEquals(6, result)
    }
}