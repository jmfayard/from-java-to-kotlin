import org.junit.jupiter.api.Test;
import pin.PinGuesser;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PinGuesserTest {
    @Test
    void handlesPin1() {
        Set<String> expected = Set.of("1", "2", "4");
        PinGuesser pinGuesser = new PinGuesser();
        Set<String> actual = pinGuesser.getPINs("1");
        assertEquals(expected, actual);
    }

    @Test
    void handlesPin2() {
        Set<String> expected = Set.of("1", "2", "3", "5");
        PinGuesser pinGuesser = new PinGuesser();
        Set<String> actual = pinGuesser.getPINs("2");
        assertEquals(expected, actual);
    }


}
