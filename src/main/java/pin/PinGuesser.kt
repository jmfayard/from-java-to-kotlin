package pin

import java.util.HashMap
import pin.PinGuesser
import java.lang.RuntimeException
import java.util.stream.Collectors

class PinGuesser {
    companion object {
        var mapPins = mapOf(
            "1" to setOf("1", "2", "4"),
            "2" to setOf("1", "2", "3", "5"),
            "3" to setOf("2", "3", "6"),
            "4" to setOf("1", "4", "5", "7"),
            "5" to setOf("2", "5", "4", "6", "8"),
            "6" to setOf("3", "5", "6", "9"),
            "7" to setOf("4", "7", "8"),
            "8" to setOf("5", "7", "8", "0"),
            "9" to setOf("6", "8", "9"),
            "0" to setOf("0", "8"),
        )
    }

    fun getPINs(observedPin: String): Set<String> {
        for (c in observedPin.toCharArray()) {
            if (!mapPins.containsKey(c.toString() + "")) throw RuntimeException("PIN $observedPin contains invalid character $c")
        }
        if (observedPin.isEmpty()) {
            return setOf()
        }
        val pins1 = mapPins[observedPin[0].toString() + ""]!!
        return if (observedPin.length == 1) {
            pins1
        } else {
            combineSolutions(pins1, getPINs(observedPin.substring(1)))
        }
    }

    fun combineSolutions(pins1: Set<String>, pins2: Set<String>): Set<String> {
        return pins1.stream()
            .flatMap { pin1: String ->
                pins2
                    .stream()
                    .map { pin2: String -> pin1 + pin2 }
            }
            .collect(Collectors.toSet())
    }
}