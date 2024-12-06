class GuardStuckException : Throwable()

class Day06: Day {
    override fun part1(input: List<String>): Int {
        val mapHeight = input.size
        val mapLength = input.first().length

        val obstacles = mutableListOf<Pair<Int, Int>>()
        var guardPosition = 0 to 0

        input.forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                if(c=='#') obstacles.add(x to y)
                if(c=='^') guardPosition = x to y
            }
        }

        val guard = Guard('^', guardPosition.first, guardPosition.second, obstacles)
        val visitedPositions = preditctPositions(mapLength, mapHeight, guard)

        return visitedPositions.size
    }

    override fun part2(input: List<String>): Int {
        val mapHeight = input.size
        val mapLength = input.first().length

        val obstacles = mutableListOf<Pair<Int, Int>>()
        var guardPosition = 0 to 0

        input.forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                if(c=='#') obstacles.add(x to y)
                if(c=='^') guardPosition = x to y
            }
        }

        var stuckCount = 0

        val guard = Guard('^', guardPosition.first, guardPosition.second, obstacles)
        val paths = preditctPositions(mapLength, mapHeight, guard)

        for (position in paths) {
            try {
                guard.obstacles = obstacles
                guard.obstacles = obstacles + position
                guard.reset('^', guardPosition.first, guardPosition.second)
                preditctPositions(mapLength, mapHeight, guard)
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
    day06.part1(input).println()

    val startTime = System.currentTimeMillis()
    day06.part2(input).println()
    val endTime = System.currentTimeMillis()
    val elapsedTimeInSeconds = (endTime - startTime) / 1000.0
    println("Tempo di esecuzione: $elapsedTimeInSeconds secondi")
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

    fun move() {
        var nextX = x
        var nextY = y

        when(orientation){
            '^' -> nextY--
            'v' -> nextY++
            '<' -> nextX--
            else -> nextX++
        }

        if(obstacles.contains(nextX to nextY)) turn()
        else {
            x = nextX
            y = nextY
        }
    }

    fun reset(resetFacing: Char, resetX: Int, resetY: Int,) {
        this.orientation = resetFacing
        this.x = resetX
        this.y = resetY
    }

}


fun preditctPositions(mapLenght: Int,mapHeight: Int, guard: Guard): Set<Pair<Int, Int>> {

        var setPositions = mutableSetOf(guard.position())
        val allPositions = mutableListOf(guard.position())

        var outside = false
        while (!outside){
            val prevPosition = guard.position()
            guard.move()
            val (x, y) = guard.position()
            if(prevPosition == x to y) allPositions.add(x to y)
            if(x < 0 || y < 0 || x == mapLenght || y == mapHeight) outside = true
            else setPositions.add(guard.position())

            var checkSize = 8
            while(checkSize<=allPositions.size){
                    val lastEqualsElements = (allPositions.size - 1 downTo allPositions.size - checkSize/2).count { index ->
                        allPositions[index] == allPositions[index - checkSize/2]
                    }
                    if(lastEqualsElements == checkSize/2) throw GuardStuckException()
                checkSize+=2
            }
        }

        return setPositions
}
