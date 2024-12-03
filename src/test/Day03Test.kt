import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day03Test {

    @Test
    fun `extract correct instructions mul`() {
        val curruptedInput = "xmul(752,709)%&mul[3,7]!@^do"

        val result = instructions(curruptedInput)

        assertEquals(listOf("mul(752,709)"), result)
    }

    @Test
    fun `extract correct instructions with enabler`() {
        val curruptedInput = "xmul(87,12)%&do()?mul(8,5)"

        val result = instructionsWithEnablers(curruptedInput)

        assertEquals(listOf("mul(87,12)", "do()", "mul(8,5)"), result)
    }


    @Test
    fun `filter disabled instructions`() {
        val curruptedInput = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"

        val instructions = instructionsWithEnablers(curruptedInput)
        val result = getEnabledInstructions(instructions)

        assertEquals(listOf("mul(2,4)", "mul(8,5)"), result)
    }

    @Test
    fun `extract correct instructions with disabler`() {
        val curruptedInput = "xmul(87,12)%&don't()?mul(8,5)"

        val result = instructionsWithEnablers(curruptedInput)

        assertEquals(listOf("mul(87,12)", "don't()", "mul(8,5)"), result)
    }


    @Test
    fun `execute instruction`() {
        val instruction = "mul(2,4)"

        val result = execute(instruction)

        assertEquals(result, 8)
    }


    @Test
    fun `Day03 part 1`() {
        val day03 = Day03()
        val instruction = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))\n"

        val result = day03.part1(listOf(instruction))

        assertEquals( 161, result)
    }


    @Test
    fun `Day03 part 2`() {
        val day03 = Day03()
        val instruction = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"

        val result = day03.part2(listOf(instruction))

        assertEquals( 48, result)
    }

}