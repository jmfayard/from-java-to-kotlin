import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pin.PinGuesser;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PinGuesserTest {
    PinGuesser pinGuesser = new PinGuesser();

    private static Stream<Arguments> testSingleDigitParameters() {
        return Stream.of(
                Arguments.of("1", Set.of("1", "2", "4")),
                Arguments.of("2", Set.of("1", "2", "3", "5")),
                Arguments.of("3", Set.of("2", "3", "6")),
                Arguments.of("4", Set.of("1", "4", "5","7")),
                Arguments.of("5", Set.of("2", "5", "4","6","8")),
                Arguments.of("6", Set.of("3", "5","6","9")),
                Arguments.of("7", Set.of("4", "7","8")),
                Arguments.of("8", Set.of( "5", "7","8","0")),
                Arguments.of("9", Set.of("6","8","9")),
                Arguments.of("0", Set.of("0","8")),
                Arguments.of("00", Set.of("00", "08","80","88"))

        );


    }

    @ParameterizedTest
    @MethodSource("testSingleDigitParameters")
    void testSingleDigit(String observedPin, Set<String> expected) {
        Set<String> actual = pinGuesser.getPINs(observedPin);
        assertEquals(expected, actual);
    }

    @Test
    void testInvalidInput() {
        assertThrows(RuntimeException.class, () -> pinGuesser.getPINs("A"));
    }
}
