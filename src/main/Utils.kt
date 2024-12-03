import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

interface Day {
    fun part1(input: List<String>): Int
    fun part2(input: List<String>): Int
}

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/main/$name.txt").readText().trim().lines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)
