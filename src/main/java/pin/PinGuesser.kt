package pin

import java.util.stream.Collectors

class PinGuesser {
    companion object {
        var mapPins: MutableMap<String, Set<String>> = HashMap()

        init {
            mapPins["1"] = java.util.Set.of("1", "2", "4")
            mapPins["2"] = java.util.Set.of("1", "2", "3", "5")
            mapPins["3"] = java.util.Set.of("2", "3", "6")
            mapPins["4"] = java.util.Set.of("1", "4", "5", "7")
            mapPins["5"] = java.util.Set.of("2", "5", "4", "6", "8")
            mapPins["6"] = java.util.Set.of("3", "5", "6", "9")
            mapPins["7"] = java.util.Set.of("4", "7", "8")
            mapPins["8"] = java.util.Set.of("5", "7", "8", "0")
            mapPins["9"] = java.util.Set.of("6", "8", "9")
            mapPins["0"] = java.util.Set.of("0", "8")
        }
    }

    fun getPINs(observedPin: String): Set<String> {
        for (c in observedPin.toCharArray()) {
            if (!mapPins.containsKey(c.toString() + "")) throw RuntimeException("PIN $observedPin contains invalid character $c")
        }
        if (observedPin.isEmpty()) {
            return java.util.Set.of()
        }
        val pins1 = mapPins[observedPin[0].toString() + ""]!!
        return if (observedPin.length == 1) {
            pins1
        } else {
            combineSolutions(pins1, getPINs(observedPin.substring(1)))
        }
    }

    private fun combineSolutions(pins1: Set<String>, pins2: Set<String>): Set<String> {
        return pins1.stream()
            .flatMap { pin1: String ->
                pins2
                    .stream()
                    .map { pin2: String -> pin1 + pin2 }
            }
            .collect(Collectors.toSet())
    }
}