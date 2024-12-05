class Day05: Day {
    override fun part1(input: List<String>): Int {
        val separator = input.indexOf("");
        val allPages = input.drop(separator+1).map { it.split(",").map(String::toInt) }
        val rules = input.subList(0,separator)
        val orderedPages = allPages.filter { pagesAreOrdered(it,rules) }
        val middlePages = orderedPages.map { it[it.size/2] }
        return middlePages.sum();
    }

    override fun part2(input: List<String>): Int {
        val separator = input.indexOf("");
        val allPages = input.drop(separator+1).map { it.split(",").map(String::toInt) }
        val rules = input.subList(0,separator)
        val unorderedPages = allPages.filter { !pagesAreOrdered(it,rules) }
        val orderedPages = unorderedPages.map { sortPages(it, rules) }
        val middlePages = orderedPages.map { it[it.size/2] }
        return middlePages.sum();
    }

}

fun pagesAreOrdered(pages: List<Int>, pagesOrderRules: List<String>): Boolean {
    for ((index, page) in pages.withIndex()){
        for (nextPage in pages.subList(index+1, pages.size)) {
            if (!pagesOrderRules.contains("$page|$nextPage")) {
                return false;
            }
        }
    }

    return true;
}

fun sortPages(pages: List<Int>, pagesOrderRules: List<String>): List<Int> {
    val sortedPages = mutableListOf<Int>()

    for ((index, page) in pages.withIndex()){
        for (nextPage in pages.subList(index+1, pages.size)) {
            if (!pagesOrderRules.contains("$page|$nextPage")) {

                val sortedIndex = sortedPages.indexOfFirst { pagesOrderRules.contains("$nextPage|$it") }
                if(sortedIndex >= 0) {
                    if(!sortedPages.contains(nextPage)) sortedPages.add(sortedIndex, nextPage)
                } else {
                    sortedPages.add(nextPage)
                }
            }
        }

        if(!sortedPages.contains(page)) sortedPages.add(page)
    }

    return sortedPages
}

fun main() {
    val day05 = Day05()

    val input = readInput("Day05")
    day05.part1(input).println()
    day05.part2(input).println()
}