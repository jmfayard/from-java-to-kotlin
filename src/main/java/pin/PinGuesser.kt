package pin

val mapPins: Map<Char, Set<String>> = mapOf(
    '0' to setOf("0", "8"),
    '1' to setOf("1", "2", "4"),
    '2' to setOf("1", "2", "3", "5"),
    '3' to setOf("2", "3", "6"),
    '4' to setOf("1", "4", "5", "7"),
    '5' to setOf("2", "5", "4", "6", "8"),
    '6' to setOf("3", "5", "6", "9"),
    '7' to setOf("4", "7", "8"),
    '8' to setOf("5", "7", "8", "0"),
    '9' to setOf("6", "8", "9"),
)

fun getPINs(observedPin: String): Set<String> {
    require(observedPin.all { it in mapPins }) { "PIN $observedPin is invalid" }

    return observedPin.fold(initial = setOf("")) { acc: Set<String>, c: Char ->
        val pinsForChar: Set<String> = mapPins[c]!!
        combineSolutions(acc, pinsForChar)
    }
}


fun combineSolutions(pins1: Set<String>, pins2: Set<String>): Set<String> =
    pins1.flatMap { pin1 ->
        pins2.map { pin2 ->
            "$pin1$pin2"
        }
    }.toSortedSet()