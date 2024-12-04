class Day04: Day {

    
    override fun part1(input: List<String>): Int {
        var count = 0;
        for (rowIndex in input.indices) {
            for (columnIndex in input[rowIndex].indices) {
                val word = "XMAS"
                if(horizontal(input, columnIndex, rowIndex, word)) count++
                if(vertical(input, columnIndex, rowIndex, word)) count++
                if(diagonalLeft(input, columnIndex, rowIndex, word)) count++
                if(diagonalRight(input, columnIndex, rowIndex, word)) count++
            }
        }

        return count
    }

    override fun part2(input: List<String>): Int {
        var count = 0;

        for (rowIndex in input.indices) {
            for (columnIndex in input[rowIndex].indices) {
                if(input[rowIndex][columnIndex] == 'A'){
                    val word = "SAM"
                    if(findXShape(input, columnIndex, rowIndex, word)) {
                        count ++
                    }
                }
            }
        }

        return count
    }

    private fun findXShape(
        input: List<String>,
        columnIndex: Int,
        rowIndex: Int,
        word: String
    ) = diagonalRight(input, columnIndex - 1, rowIndex - 1, word) &&
            diagonalLeft(input, columnIndex + 1, rowIndex - 1, word)
}

fun main() {
    val day04 = Day04()

    val input = readInput("Day04")
    day04.part1(input).println()
    day04.part2(input).println()
}

fun horizontal(input: List<String>, from: Int, rowIndex: Int = 0, word: String): Boolean {
    val substring = input[rowIndex].substring(from)
    return substring.startsWith(word) || substring.startsWith(word.reversed())
}


fun vertical(input: List<String>, colIndex: Int = 0, rowIndex: Int = 0, word: String): Boolean {
    try {
        val toCheck: String = "" + input[rowIndex][colIndex] + input[rowIndex+1][colIndex] + input[rowIndex+2][colIndex] + input[rowIndex+3][colIndex]
        return toCheck == word || toCheck == word.reversed()
    }catch (ex: IndexOutOfBoundsException){
        return false
    }
}

fun diagonalRight(input: List<String>, colIndex: Int = 0, rowIndex: Int = 0, word: String): Boolean {
    try {
        var toCheck = ""
        for(i in word.indices){
            toCheck+= input[rowIndex+i][colIndex+i]
        }
        return toCheck == word || toCheck == word.reversed()
    }catch (ex: IndexOutOfBoundsException){
        return false
    }
}

fun diagonalLeft(input: List<String>, colIndex: Int = 0, rowIndex: Int = 0, word: String): Boolean {
    try {
        var toCheck = ""
        for(i in word.indices){
            toCheck+= input[rowIndex+i][colIndex-i]
        }
        return toCheck == word || toCheck == word.reversed()
    }catch (ex: IndexOutOfBoundsException){
        return false
    }
}