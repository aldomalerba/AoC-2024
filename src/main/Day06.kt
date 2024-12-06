class Day06: Day {
    override fun part1(input: List<String>): Int {
        val mapHeight = input.size
        val mapLength = input.first().length
        var guardPosition = 0 to 0
        val obstacles = mutableListOf<Pair<Int, Int>>()

        input.forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                if(c=='#') obstacles.add(x to y)
                if(c=='^') guardPosition = x to y
            }
        }

        val guard = Guard('^', guardPosition.first, guardPosition.second, obstacles)
        val predictor = GuardPositionsPredictor(mapLength, mapHeight, guard)
        return predictor.execute().size
    }

    override fun part2(input: List<String>): Int {
        TODO("Not yet implemented")
    }

}

fun main() {
    val day06 = Day06()

    val input = readInput("Day06")
    day06.part1(input).println()
    //day04.part2(input).println()
}

class Guard(facing: Char, x: Int, y: Int,val obstacles: List<Pair<Int,Int>> = emptyList()) {
    private val turnRules = mapOf('^' to '>', '>' to 'v', 'v' to '<', '<' to '^')

    private var x = x
    private var y = y


    var orientation = facing
        private set

    fun turn() {
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


class GuardPositionsPredictor(val mapLenght: Int,val mapHeight: Int,val guard: Guard) {

    private var setPositions = mutableSetOf(guard.position())

    fun execute(): Set<Pair<Int, Int>> {
        var outside = false
        while (outside == false){
            guard.move()
            val (x, y) = guard.position()
            if(x < 0 || y < 0 || x == mapLenght || y == mapHeight) outside = true
            else setPositions.add(guard.position())
        }

        return setPositions
    }

}
