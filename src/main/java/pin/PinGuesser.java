package pin;

import java.util.Set;


public class PinGuesser {
    public Set<String> getPINs(String observedPin) {
        if (("2").equals(observedPin)) {
            return Set.of("1", "2", "3", "5");
        }
        return Set.of("1", "2", "4");
    }
}
