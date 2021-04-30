package pin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class PinGuesser {
    static Map<String, Set<String>> mapPins = new HashMap<>();

    static {
        mapPins.put("1", Set.of("1", "2", "4"));
        mapPins.put("2", Set.of("1", "2", "3", "5"));
        mapPins.put("3", Set.of("2", "3", "6"));
        mapPins.put("4", Set.of("1", "4", "5", "7"));
        mapPins.put("5", Set.of("2", "5", "4", "6", "8"));
        mapPins.put("6", Set.of("3", "5", "6", "9"));
        mapPins.put("7", Set.of("4", "7", "8"));
        mapPins.put("8", Set.of("5", "7", "8", "0"));
        mapPins.put("9", Set.of("6", "8", "9"));
        mapPins.put("0", Set.of("0", "8"));
    }

    public Set<String> getPINs(String observedPin) {
        if (observedPin.isEmpty()){
            return Set.of();
        }
        if (observedPin.length() == 2) {
            Set<String> pin1 = getPINs(observedPin.charAt(0) + "");
            Set<String> pin2 = getPINs(observedPin.charAt(1) + "");
            return combineSolutions(pin1, pin2);
        }
        if (observedPin.length() == 1) {
            if (!mapPins.containsKey(observedPin)) {
                throw new RuntimeException("Invalid pin: " + observedPin);

            }
            return mapPins.get(observedPin);
        }
        throw new RuntimeException("Invalid pin: " + observedPin);
    }

    private Set<String> combineSolutions(Set<String> pins1, Set<String> pins2) {
        return pins1.stream()
                .flatMap((pin1) ->
                        pins2
                                .stream()
                                .map((pin2) -> pin1 + pin2))
                .collect(Collectors.toSet());
    }
}
