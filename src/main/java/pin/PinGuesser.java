package pin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class PinGuesser {
    static Map<String, Set<String>> mapPins = new HashMap<>();
    static {
        mapPins.put("1", Set.of("1", "2", "4"));
        mapPins.put("2", Set.of("1", "2", "3", "5"));
        mapPins.put("3", Set.of("2", "3", "6"));
    }

    public Set<String> getPINs(String observedPin) {
        if (!mapPins.containsKey(observedPin)) {
            throw new RuntimeException("Invalid pin: " + observedPin);
        }
        return mapPins.get(observedPin);
    }
}
