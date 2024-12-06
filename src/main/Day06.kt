class GuardStuckException : Throwable()

class Day06: Day {
    override fun part1(input: List<String>): Int {

        val obstacles = mutableListOf<Pair<Int, Int>>()
        var guardPosition = 0 to 0

        input.forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                if(c=='#') obstacles.add(x to y)
                if(c=='^') guardPosition = x to y
            }
        }

        val guard = Guard('^', guardPosition.first, guardPosition.second, obstacles)
        val changeOrientationPositions = preditctPositions(guard)
        val result = joinPath(changeOrientationPositions, input)

        return result.size
    }

    private fun joinPath(
        changeOrientationPositions: List<Pair<Pair<Int, Int>, Char>>,
        input: List<String>
    ): MutableSet<Pair<Int, Int>> {
        val result = mutableSetOf<Pair<Int, Int>>()
        for ((index, pos) in changeOrientationPositions.withIndex()) {
            val (x, y) = pos.first
            val (nextX, nextY) = changeOrientationPositions.getOrNull(index + 1)?.first ?: (null to null)
            when (pos.second) {
                '^' -> (y downTo (nextY ?: 0)).forEach { result.add(x to it) }
                '>' -> (x..(nextX ?: (input.first().length - 1))).forEach { result.add(it to y) }
                'v' -> (y..(nextY ?: (input.size - 1))).forEach { result.add(x to it) }
                else -> (x downTo (nextX ?: 0)).forEach { result.add(it to y) }
            }
        }
        return result
    }

    override fun part2(input: List<String>): Int {

        val obstacles = mutableListOf<Pair<Int, Int>>()
        var guardPosition = 0 to 0

        input.forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                if(c=='#') obstacles.add(x to y)
                if(c=='^') guardPosition = x to y
            }
        }


        val guard = Guard('^', guardPosition.first, guardPosition.second, obstacles)
        val changeOrientationPositions = preditctPositions(guard)
        val paths = joinPath(changeOrientationPositions, input)

        var stuckCount = 0
        for (position in paths) {
            try {
                if(position == 7 to 9){
                    println("asdas")
                }
                guard.obstacles = obstacles
                guard.obstacles = obstacles + position
                guard.reset('^', guardPosition.first, guardPosition.second)
                preditctPositions(guard)
            }catch (e: GuardStuckException) {
                stuckCount++
            }
        }

        return stuckCount

    }

}

fun main() {
    val day06 = Day06()

    val input = readInput("Day06")
    val startTime = System.currentTimeMillis()
    day06.part1(input).println()
    day06.part2(input).println()
    val endTime = System.currentTimeMillis()
    val elapsedTimeInSeconds = (endTime - startTime) / 1000.0
    println("Time: $elapsedTimeInSeconds second")
}

class Guard(facing: Char, x: Int, y: Int, var obstacles: List<Pair<Int,Int>> = emptyList()) {
    private val turnRules = mapOf('^' to '>', '>' to 'v', 'v' to '<', '<' to '^')

    private var x = x
    private var y = y


    var orientation = facing
        private set

    private fun turn() {
        orientation = turnRules[orientation]!!
    }

    fun position(): Pair<Int, Int> {
        return x to y
    }

    fun move(): Pair<Int, Int> {
        var nextX = x
        var nextY = y

        when(orientation){
            '^' -> {
                val nextPosition = obstacles.filter { it.first == nextX && nextY > it.second }.maxByOrNull { it.second }
                nextY = if(nextPosition != null) nextPosition.second + 1 else -1
                turn()
            }
            'v' -> {
                val nextPosition = obstacles.filter { it.first == nextX && nextY < it.second }.minByOrNull { it.second }
                nextY = if(nextPosition != null) nextPosition.second -1 else -1
                turn()
            }
            '<' -> {
                val nextPosition = obstacles.filter { it.second == nextY && nextX > it.first }.maxByOrNull { it.first }
                nextX = if(nextPosition != null) nextPosition.first + 1 else -1
                turn()
            }
            else -> {
                val nextPosition = obstacles.filter { it.second == nextY && nextX < it.first }.minByOrNull { it.first }
                nextX = if(nextPosition != null) nextPosition.first - 1 else -1
                turn()
            }
        }


        x = nextX
        y = nextY

        return nextX to nextY
    }

    fun reset(resetFacing: Char, resetX: Int, resetY: Int,) {
        this.orientation = resetFacing
        this.x = resetX
        this.y = resetY
    }

}


fun preditctPositions(guard: Guard): List<Pair<Pair<Int, Int>,Char>> {

        val allPositions = mutableListOf(guard.position() to guard.orientation)

        var outside = false
        while (!outside){
            val (x, y) = guard.move()
            if(x < 0 || y < 0) outside = true else allPositions.add((x to y) to guard.orientation)

            var checkSize = 8
            while(checkSize<=allPositions.size){
                    val lastEqualsElements = (allPositions.size - 1 downTo allPositions.size - checkSize/2).count { index ->
                        allPositions[index].first == allPositions[index - checkSize/2].first
                    }
                    if(lastEqualsElements == checkSize/2) throw GuardStuckException()
                checkSize+=2
            }
        }

        return allPositions
}
