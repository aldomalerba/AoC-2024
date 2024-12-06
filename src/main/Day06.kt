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
        val predictor = GuardPositionsPredictor(mapLength, mapHeight, guard)
        val visitedPositions = predictor.execute()

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
        val predictor = GuardPositionsPredictor(mapLength, mapHeight, guard)
        val paths = predictor.execute()

        for (position in paths) {
            try {
                val guard = Guard('^', guardPosition.first, guardPosition.second, obstacles + position)
                val predictor = GuardPositionsPredictor(mapLength, mapHeight, guard)
                predictor.execute()
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
    day06.part2(input).println()
}

class Guard(facing: Char, x: Int, y: Int,val obstacles: List<Pair<Int,Int>> = emptyList()) {
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

}


class GuardPositionsPredictor(private val mapLenght: Int, private val mapHeight: Int, val guard: Guard) {

    private var setPositions = mutableSetOf(guard.position())
    private val allPositions = mutableListOf(guard.position())

    fun execute(): Set<Pair<Int, Int>> {
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

}
